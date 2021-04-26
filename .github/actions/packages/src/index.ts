import {getInput, setFailed} from '@actions/core'
import {Observable, throwError} from 'rxjs'
import {
  deletePackagesVersions,
  searchPackagesVersions,
  SearchPackageVersionRequest
} from './packageService'
import {concatMap} from 'rxjs/operators'
import packages from './packages'

function run(): Observable<boolean> {
  try {
    const searchPackageVersionRequest: SearchPackageVersionRequest = {
      token: getInput('token'),
      owner: getInput('owner'),
      repository: getInput('repository'),
      //packages: getInput('packageNames').split(','),
      packages: packages,
      versions: parseInt(getInput('versions'))
    }
    return searchPackagesVersions(searchPackageVersionRequest).pipe(
      concatMap(r =>
        deletePackagesVersions({
          token: searchPackageVersionRequest.token,
          versions: r.reverse()
        })
      )
    )
  } catch (error) {
    return throwError(error.message)
  }
}

run().subscribe({
  error: err => {
    setFailed(err)
  }
})

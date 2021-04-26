import {
  deletePackagesVersions,
  searchPackagesVersions,
  SearchPackageVersionRequest
} from '../src/packageService'
import packages from '../src/packages'
import {tap} from 'rxjs/operators'

describe('Package Operation Service', () => {
  it('Search versions', done => {
    searchPackagesVersions({
      token: '14326d0a2378b19de3ee5989c7775468d0106304',
      owner: 'andresmontoyat',
      repository: 'codehunters-io-commons',
      packages: packages,
      versions: 1
    } as SearchPackageVersionRequest).subscribe(result => {
      console.log(result)
      done()
    })
  })

  it('Delete versions', done => {
    searchPackagesVersions({
      token: '14326d0a2378b19de3ee5989c7775468d0106304',
      owner: 'andresmontoyat',
      repository: 'codehunters-io-commons',
      packages: packages,
      versions: 1
    } as SearchPackageVersionRequest).subscribe(result => {
      deletePackagesVersions({
        token: '14326d0a2378b19de3ee5989c7775468d0106304',
        versions: result.reverse()
      }).subscribe(result => {
        done()
      })
    })
  })
})

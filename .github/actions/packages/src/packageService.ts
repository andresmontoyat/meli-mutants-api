import {graphql} from './graphql'
import {from, merge, Observable, throwError} from 'rxjs'
import {catchError, map} from 'rxjs/operators'

export interface SearchPackageVersionRequest {
  token: string
  owner: string
  repository: string
  packages: string[]
  versions: number
}

export interface PackageVersionRequest {
  token: string
  version: VersionInfo
}

export interface PackageVersionsRequest {
  token: string
  versions: VersionInfo[]
}

export interface VersionInfo {
  id: string
  version: string
}

export interface SearchPackageVersionResponse {
  repository: {
    packages: {
      edges: {
        node: {
          name: string
          versions: {
            edges: {
              node: VersionInfo
            }[]
          }
        }
      }[]
    }
  }
}

export interface DeleteVersionResponse {
  id: string
  success: boolean
}

export function deletePackageVersion(
  request: PackageVersionRequest
): Observable<boolean> {
  const query = `mutation deletePackageVersion($versionId: String!) {
      deletePackageVersion(input: {packageVersionId: $versionId}) {
        success
      }
  }`
  return from(
    graphql(request.token, query, {
      versionId: request.version.id,
      headers: {
        Accept: 'application/vnd.github.package-deletes-preview+json'
      }
    }) as Promise<DeleteVersionResponse>
  ).pipe(
    map(response => response.success),
    catchError(err => {
      console.log(err)
      return throwError(
        'An error has occurred trying to delete package version'
      )
    })
  )
}

export function deletePackagesVersions(
  request: PackageVersionsRequest
): Observable<boolean> {
  const p = request.versions.map(version =>
    deletePackageVersion({token: request.token, version: version})
  )
  return merge(...p)
}

export function searchPackagesVersions(
  request: SearchPackageVersionRequest
): Observable<VersionInfo[]> {
  const query = `
    query versions($owner: String!, $repository: String!, $packages: [String]!, $packagesLen: Int!, $versions: Int!) {
      repository(owner: $owner, name: $repository) {
        packages(first: $packagesLen, names: $packages) {
          edges {
            node {
              name
              versions(last: $versions) {
                edges {
                  node {
                    id
                    version
                  }
                }
              }
            }
          }
        }
      }
    }`

  return from(
    graphql(request.token, query, {
      owner: request.owner,
      repository: request.repository,
      packages: request.packages,
      packagesLen: request.packages.length,
      versions: request.versions,
      headers: {
        Accept: 'application/vnd.github.packages-preview+json'
      }
    }) as Promise<SearchPackageVersionResponse>
  ).pipe(
    map(response => {
      const packages = response.repository.packages.edges
      if (packages.length === 0) {
        throwError('')
      }

      const versions = new Array<VersionInfo>()
      //Skip versions
      packages
        .filter(p => {
          return p.node.versions.edges.length === request.versions
        })
        .forEach(p => {
          p.node.versions.edges.forEach(v => versions.push(v.node))
        })
      return versions
    }),
    catchError(err => {
      console.log(err)
      return throwError(
        'An error has occurred trying to search packages versions'
      )
    })
  )
}

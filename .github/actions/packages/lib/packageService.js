"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.searchPackagesVersions = exports.deletePackagesVersions = exports.deletePackageVersion = void 0;
const graphql_1 = require("./graphql");
const rxjs_1 = require("rxjs");
const operators_1 = require("rxjs/operators");
function deletePackageVersion(request) {
    const query = `mutation deletePackageVersion($versionId: String!) {
      deletePackageVersion(input: {packageVersionId: $versionId}) {
        success
      }
  }`;
    return rxjs_1.from(graphql_1.graphql(request.token, query, {
        versionId: request.version.id,
        headers: {
            Accept: 'application/vnd.github.package-deletes-preview+json'
        }
    })).pipe(operators_1.map(response => response.success), operators_1.catchError(err => {
        console.log(err);
        return rxjs_1.throwError('An error has occurred trying to delete package version');
    }));
}
exports.deletePackageVersion = deletePackageVersion;
function deletePackagesVersions(request) {
    const p = request.versions.map(version => deletePackageVersion({ token: request.token, version: version }));
    return rxjs_1.merge(...p);
}
exports.deletePackagesVersions = deletePackagesVersions;
function searchPackagesVersions(request) {
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
    }`;
    return rxjs_1.from(graphql_1.graphql(request.token, query, {
        owner: request.owner,
        repository: request.repository,
        packages: request.packages,
        packagesLen: request.packages.length,
        versions: request.versions,
        headers: {
            Accept: 'application/vnd.github.packages-preview+json'
        }
    })).pipe(operators_1.map(response => {
        const packages = response.repository.packages.edges;
        if (packages.length === 0) {
            rxjs_1.throwError('');
        }
        const versions = new Array();
        //Skip versions
        packages
            .filter(p => {
            return p.node.versions.edges.length === request.versions;
        })
            .forEach(p => {
            p.node.versions.edges.forEach(v => versions.push(v.node));
        });
        return versions;
    }), operators_1.catchError(err => {
        console.log(err);
        return rxjs_1.throwError('An error has occurred trying to search packages versions');
    }));
}
exports.searchPackagesVersions = searchPackagesVersions;

"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@actions/core");
const rxjs_1 = require("rxjs");
const packageService_1 = require("./packageService");
const operators_1 = require("rxjs/operators");
const packages_1 = __importDefault(require("./packages"));
function run() {
    try {
        const searchPackageVersionRequest = {
            token: core_1.getInput('token'),
            owner: core_1.getInput('owner'),
            repository: core_1.getInput('repository'),
            //packages: getInput('packageNames').split(','),
            packages: packages_1.default,
            versions: parseInt(core_1.getInput('versions'))
        };
        return packageService_1.searchPackagesVersions(searchPackageVersionRequest).pipe(operators_1.concatMap(r => packageService_1.deletePackagesVersions({
            token: searchPackageVersionRequest.token,
            versions: r.reverse()
        })));
    }
    catch (error) {
        return rxjs_1.throwError(error.message);
    }
}
run().subscribe({
    error: err => {
        core_1.setFailed(err);
    }
});

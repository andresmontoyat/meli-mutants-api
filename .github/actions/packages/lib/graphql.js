"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.graphql = void 0;
const github_1 = require("@actions/github");
/**
 * Sends a GraphQL query request based on endpoint options
 *
 * @param {string} token Auth token
 * @param {string} query GraphQL query. Example: `'query { viewer { login } }'`.
 * @param {object} parameters URL, query or body parameters, as well as `headers`, `mediaType.{format|previews}`, `request`, or `baseUrl`.
 */
function graphql(token, query, parameters) {
    return __awaiter(this, void 0, void 0, function* () {
        const github = new github_1.GitHub(token);
        return yield github.graphql(query, parameters);
    });
}
exports.graphql = graphql;

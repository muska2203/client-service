/* tslint:disable */
/* eslint-disable */
/**
 * Application API
 * desc
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: test@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface ClientCreateRequest
 */
export interface ClientCreateRequest {
    /**
     * 
     * @type {string}
     * @memberof ClientCreateRequest
     */
    name: string;
    /**
     * 
     * @type {string}
     * @memberof ClientCreateRequest
     */
    phoneNumber: string;
}

/**
 * Check if a given object implements the ClientCreateRequest interface.
 */
export function instanceOfClientCreateRequest(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "name" in value;
    isInstance = isInstance && "phoneNumber" in value;

    return isInstance;
}

export function ClientCreateRequestFromJSON(json: any): ClientCreateRequest {
    return ClientCreateRequestFromJSONTyped(json, false);
}

export function ClientCreateRequestFromJSONTyped(json: any, ignoreDiscriminator: boolean): ClientCreateRequest {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'name': json['name'],
        'phoneNumber': json['phoneNumber'],
    };
}

export function ClientCreateRequestToJSON(value?: ClientCreateRequest | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'name': value.name,
        'phoneNumber': value.phoneNumber,
    };
}


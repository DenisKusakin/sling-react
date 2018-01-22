import axios from 'axios';

export const updateResource = ({url, props}) => {
    const params = new URLSearchParams();
    Object.keys(props).forEach(x => params.append(`./${x}`, props[x]));

    return axios({
        method: 'post',
        data: params,
        url: url,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'text/html'
        }
    })
};

export const deleteResource = ({url}) => {
    const data = new URLSearchParams();
    data.append(":operation", "delete");

    return axios({
        method: 'post',
        data,
        url: url,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
};

export const createResource = ({url, props}) => {
    const params = new URLSearchParams();
    Object.keys(props).forEach(x => params.append(x, props[x]));

    return axios({
        method: 'post',
        data: params,
        url: url,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'text/html'
        }
    })
};
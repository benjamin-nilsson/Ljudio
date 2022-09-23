import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }

    const error = new Error(response.statusText);
    error.response = response;
    response.json().then(e => {
        error.error = e;
    });
    return Promise.reject(error);
}
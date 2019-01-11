import { API_BASE_URL } from '../constants';

const request = (options) => {
  const headers = new Headers({
      'Content-Type': 'application/json',
  })

  const defaults = {headers: headers};
  options = Object.assign({}, defaults, options);

  return fetch(options.url, options)
  .then(response => 
      response.json().then(json => {
          return json;
      })
  );
};

// User methods

export function getCurrentUser() {

  return request({
      url: API_BASE_URL + "/user/me",
      method: 'GET'
  });
}

// Auth methods

export function login(loginRequest) {
  return request({
    url: API_BASE_URL + "/auth/signin",
    method: 'POST',
    body: JSON.stringify(loginRequest)
  });
}
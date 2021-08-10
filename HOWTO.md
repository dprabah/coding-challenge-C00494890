# URL shortener service
## UI
- Accessing: [localhost:8080](http://localhost:8080) opens a webpage, which asks for a username and a URL to shorten.

- Clicking statistics button, opens a statistics page.

- Statistics page also allows a user to delete a URL, by clicking delete button.

## Webservices API

- URL [POST]

```html
localhost:8080/service/shorten
```

- Request [json/application]

```json
{"user":"example-user","url":"example.com"}
```

- Response

```html
<generated unique code>
```

- URL [GET]

```html
localhost:8080/service/stats
```

- Request

```jsx
None
```

- Response

```json
{"urlCounts":[{"url":{"id":"<unique id>","url":"<example.com>","user":"example-user"},"count":1,"user":null}]}
```

- URL[GET]

```html
localhost:8080/service/delete/{user-id}/{generated-unique-id}
```

- Request

```text
user-id: user's id.
generated-unique-id: unique generated shortened URL
```

- Response

```jsx
None
```
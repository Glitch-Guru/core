# GlitchGuru Issue Tracker

## API Reference

### `POST /api/v1/auth/register`

#### Parameters

| Name        | Type     | Description                             |
|-------------|----------|-----------------------------------------|
| `firstName` | `string` | **Required**. First name of the user.   |
| `lastName`  | `string` | **Required**. Last name of the user.    |
| `email`     | `string` | **Required**. Email of the user.        |
| `password`  | `string` | **Required**. The password of the user. |

#### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImFjY291bnRfaWQiOjEsInN1YiI6ImR1cGFmc2Rkc2ZzZGFmZjEyM0BlbWZkc2ZzZGFpbC5wbCIsImlhdCI6MTY4MDk4MTgxNSwiZXhwIjoxNjgwOTgzMjU1fQ.UN349pq51V0yFduI9aar--7pPbhWEchdAfJt_SJt_Qs"
}
```

### `POST /api/v1/auth/authenticate`

#### Parameters

| Name       | Type     | Description                             |
|------------|----------|-----------------------------------------|
| `email`    | `string` | **Required**. Email of the user.        |
| `password` | `string` | **Required**. The password of the user. |

#### Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsImFjY291bnRfaWQiOjEsInN1YiI6ImR1cGFmc2Rkc2ZzZGFmZjEyM0BlbWZkc2ZzZGFpbC5wbCIsImlhdCI6MTY4MDk4MTg3NSwiZXhwIjoxNjgwOTgzMzE1fQ.F5qaaJaO7ndDtSR11AFAlraa_ZlVMFQNCaRC4b5U5i0"
}
```

### `POST /api/v1/tickets`

#### Parameters

| Name          | Type     | Description                                                |
|---------------|----------|------------------------------------------------------------|
| `title`       | `string` | **Required**. Title of the ticket.                         |
| `description` | `string` | **Required**. Content of the ticket.                       |
| `status`      | `string` | Status of the ticket. Default value: Open                  |
| `assigneeId`  | `number` | Assignee of the ticket. Default value: id of the requester |

#### Response

```json
{
  "id": 1,
  "title": "Test ticket 1",
  "description": "Test ticket 1 description",
  "status": "Open",
  "assigneeId": 1,
  "reporterId": 1
}
```

#### `GET /api/v1/tickets`

#### Response

```json
[
  {
    "id": 1,
    "title": "Test ticket 1",
    "description": "Test ticket 1 description",
    "status": "Open",
    "assigneeId": 1,
    "reporterId": 1
  },
  {
    "id": 2,
    "title": "Test ticket 2",
    "description": "Test ticket 2 description",
    "status": "Open",
    "assigneeId": 1,
    "reporterId": 1
  }
]
```

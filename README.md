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


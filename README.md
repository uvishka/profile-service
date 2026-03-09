# Profile Management Service

## Overview
The Profile Management Service manages extended profile information for registered users.

This service stores and updates profile-related data such as:

- address
- description
- website
- facebook link
- instagram link
- logo URL
- number of types of books
- genres

The service also retrieves basic user account information from the Auth Service when returning the full profile.

---

# Service Port

## Profile Service runs on:

http://localhost:8081


## Gateway access:

http://localhost:8086


---

# API Endpoints

## 1. Get Full Profile

Returns the full profile including both user account information and profile details.

### Endpoint

GET /profiles/me/full


### Gateway URL

http://localhost:8086/profiles/me/full


### Direct Service URL

http://localhost:8081/profiles/me/full


### Headers (Gateway Request)


Authorization: Bearer <JWT_TOKEN>


### Headers (Direct Service Request)


X-User-Id: 9
X-User-Email: isitha@gmail.com
X-User-Role: USER


### Example Response

```json
{
  "success": true,
  "message": "Full profile fetched successfully",
  "data": {
    "userId": 9,
    "email": "isitha@gmail.com",
    "role": "USER",
    "companyName": "Isitha Tech",
    "contactNumber": "0771234567",
    "owner": "Isitha Wijetunga",
    "address": "Colombo, Sri Lanka",
    "description": "We publish educational and fiction books.",
    "website": "https://isithatech.com",
    "facebookLink": "https://facebook.com/isithatech",
    "instagramLink": "https://instagram.com/isithatech",
    "logoUrl": "https://cdn.example.com/logo.png",
    "numberOfTypesOfBooks": 12,
    "genres": [
      "Children",
      "Fiction",
      "Science"
    ]
  }
}
```

## 2. Update Full Profile

Updates profile-specific fields for the logged-in user.

### Endpoint

PUT /profiles/me/full

### Gateway URL

http://localhost:8086/profiles/me/full

### Direct Service URL

http://localhost:8081/profiles/me/full

### Headers (Gateway Request)

Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

### Headers (Direct Service Request)

X-User-Id: 9
X-User-Email: isitha@gmail.com
X-User-Role: USER
Content-Type: application/json

### Request Body

```json
{
  "address": "Galle, Sri Lanka",
  "description": "Updated direct profile test.",
  "website": "https://directtest.com",
  "facebookLink": "https://facebook.com/directtest",
  "instagramLink": "https://instagram.com/directtest",
  "logoUrl": "https://cdn.example.com/direct-logo.png",
  "numberOfTypesOfBooks": 15,
  "genres": ["History", "Biography"]
}
```

### Example Response

```json
{
  "success": true,
  "message": "Full profile updated successfully",
  "data": {
    "userId": 9,
    "email": "isitha@gmail.com",
    "role": "USER",
    "companyName": "Isitha Tech",
    "contactNumber": "0771234567",
    "owner": "Isitha Wijetunga",
    "address": "Galle, Sri Lanka",
    "description": "Updated direct profile test.",
    "website": "https://directtest.com",
    "facebookLink": "https://facebook.com/directtest",
    "instagramLink": "https://instagram.com/directtest",
    "logoUrl": "https://cdn.example.com/direct-logo.png",
    "numberOfTypesOfBooks": 15,
    "genres": [
      "Biography",
      "History"
    ]
  }
}
```
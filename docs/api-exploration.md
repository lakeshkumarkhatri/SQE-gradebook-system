# API Exploration Notes - Lab 12

This document records manual testing and exploration of the Gradebook API endpoints using `curl` while the application was running locally on port 8080.

---

## 1. POST /students (Register a Student)
* **Request:** 
  * Method: `POST`
  * Path: `/students`
  * Headers: `Content-Type: application/json`
  * Body: `{"studentId":"S1001","name":"Ayesha Khan"}`
* **Response:**
  * Status: `201 Created`
  * Body: `{"studentId":"S1001","name":"Ayesha Khan","average":0}`

---

## 2. POST /students (Negative: Invalid Student ID - Deliberate Test)
* **Request:** 
  * Method: `POST`
  * Path: `/students`
  * Headers: `Content-Type: application/json`
  * Body: `{"studentId":"S100","name":"Invalid ID Student"}`
* **Response:**
  * Status: `400 Bad Request`
  * Body: `{"error":"studentId must be S followed by 4 digits"}`
  * *Note:* Properly caught by the regex pattern `S\d{4}`.

---

## 3. GET /students (Retrieve All Students)
* **Request:** 
  * Method: `GET`
  * Path: `/students`
* **Response:**
  * Status: `200 OK`
  * Body: `[{"studentId":"S1001","name":"Ayesha Khan","average":0}]`

---

## 4. GET /students/{studentId} (Retrieve Specific Student)
* **Request:** 
  * Method: `GET`
  * Path: `/students/S1001`
* **Response:**
  * Status: `200 OK`
  * Body: `{"studentId":"S1001","name":"Ayesha Khan","average":0}`

---

## 5. POST /students/{studentId}/scores (Record a Score)
* **Request:** 
  * Method: `POST`
  * Path: `/students/S1001/scores`
  * Headers: `Content-Type: application/json`
  * Body: `{"score":85}`
* **Response:**
  * Status: `201 Created`
  * Body: `{"studentId":"S1001","score":85,"average":85}`

---

## 6. POST /students/{studentId}/scores (Negative: Invalid Score 101 - Deliberate Test)
* **Request:** 
  * Method: `POST`
  * Path: `/students/S1001/scores`
  * Headers: `Content-Type: application/json`
  * Body: `{"score":101}`
* **Response:**
  * Status: `400 Bad Request`
  * Body: `{"error":"score must be between 0 and 100"}`

---

## 7. GET /students/{studentId}/grade (Get Student Grade & Average)
* **Request:** 
  * Method: `GET`
  * Path: `/students/S1001/grade`
* **Response:**
  * Status: `200 OK`
  * Body: `{"studentId":"S1001","average":85,"letter":"B"}`

---

## 8. GET /grade-scale (Lookup Grade Scale for a Score)
* **Request:** 
  * Method: `GET`
  * Path: `/grade-scale?score=95`
* **Response:**
  * Status: `200 OK`
  * Body: `{"score":95,"letter":"A"}`
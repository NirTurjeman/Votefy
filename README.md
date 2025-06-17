# Votefy Client Library

The **Votefy Client Library** is a Kotlin-based client library that allows both users and administrators to interact with the Votefy voting system backend through a typed and coroutine-compatible interface using Retrofit.

---

## 🧱 Architecture

The library is organized around two service interfaces:

- `VoterService`: Handles functionality for regular users (submitting votes, retrieving polls to vote in).
- `AdminService`: Provides complete administrative control over polls (creation, activation, deletion, results, etc.).

These services are initialized via Retrofit and exposed to consumers via a higher-level wrapper class (`VotefyClient`).

---

## 📦 Admin Functionality (`AdminService`)

The `AdminService` interface provides the following methods:

```kotlin
adminLogin(body: AdminInfoDto): Response<Boolean>
createVote(body: CreatePollDto): Response<Unit>
deleteVote(voteId: String): Response<Unit>
activateVote(id: String): Response<Unit>
deactivateVote(id: String): Response<Unit>
isVoteActive(id: String): Response<Boolean>
getVoters(questionId: String): Response<Array<String>>
getAllDefinitions(): Response<List<Poll>>
hasUserVoted(userId: String, questionId: String): Response<Boolean>
getResults(questionId: String): Response<Map<String, Int>>
```

### 🔐 Admin responsibilities:
- Authenticate into the system
- Create and manage poll definitions (open / multiple choice)
- Activate / deactivate polls
- Track participation and calculate poll results

---

## 👤 User Functionality (`VoterService`)

The `VoterService` interface provides the following methods:

```kotlin
vote(submission: UserVote): Response<Unit>
getOpenVotes(userId: String): Response<List<Poll>>
```

### 🧑 User responsibilities:
- Submit their vote (text or option-based)
- Fetch a list of open polls they haven’t voted on yet

---

## 📌 Models

The library relies on several shared models such as:

- `Poll`: Represents a poll fetched from the backend
- `UserVote`: Represents a user’s vote submission
- `VoteType`: Enum (`OPEN_POLL`, `MULTIPLE_CHOICE`)
- `CreatePollDto`: Used to create new polls
- `AdminInfoDto`: Admin login credentials DTO

---

---

## 🚦 Getting Started – Workflow Overview

The system is designed to support both **manual testing** and **real-time operation**.

### 1. Creating Polls

Polls can be created via:

- The **Admin Web Portal** (recommended)
- Manual API calls using the `createVote()` method in the client library

### 2. Distributing Polls

Once a poll is created, it becomes accessible to users — but **it's the developer's responsibility** to handle the logic of who sees what.

To support this, the library provides the method:

```kotlin
hasUserVoted(userId: String, questionId: String): Response<Boolean>
```

This allows developers to check whether a user has already voted in a specific poll. Based on this, they can programmatically decide **who to notify or prompt** to participate in a vote (e.g. via push notification, banner, or in-app message).

### 3. Vote Submission

Users use the mobile app or frontend interface to submit their votes via:

```kotlin
vote(submission: UserVote): Response<Unit>
```

### 4. Results and Analytics

Poll results and analytics are primarily visualized in the **Admin Dashboard** using:

- `getResults()` – returns vote distribution
- `getVoters()` – returns list of voters for a poll

While the web admin dashboard provides a full interface for managing polls and analyzing results,  
**the client library also exposes all relevant endpoints**, allowing developers to build custom management interfaces —  
such as mobile admin views or in-app analytics — if desired.
---

<template>

  <!-- Editing a request. Hidden by default, unless they click.-->

  <div v-if="currentRequestId">
    <h2> Editing Request {{ currentRequestId }} </h2>
    
    <edit-request></edit-request>
    <button @click="currentRequestId = null">Close</button>
  </div>

  <!-- Show all requests-->
  <div>
    <label for="filter">Status:</label>
    <select id="filter" v-model="selectedFilter" @change="getRequests">
      <option value="">All</option>
      <option value="APPROVED">Approved</option>
      <option value="PENDING">Pending</option>
      <option value="REJECTED">Rejected</option>
    </select>

<table>
  <thead>
    <tr>
      <th>Request ID</th>
      <th>Customer Name</th>
      <th>Event Date</th>
      <th>Event Title</th>
      <th>Request Status</th>
      <th>Assigned SuperFrog</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="request in computedRequests" :key="request.id">
      <td>{{ request.requestId }}</td>
      <td>{{ request.contactFirstName }} {{ request.contactLastName }}</td>
      <td>{{ request.eventDate }}</td>
      <td>{{ request.title }}</td>
      <td>{{ request.status }}</td>
      <td>{{ request.student ? request.student.firstName + ' ' + request.student.lastName : 'None' }}</td>
      <td>
        <button @click="approveRequest(request)">Approve</button>
        <button @click="rejectRequest(request)">Reject</button>
        <button @click="editRequest(request.requestId)">Edit Request</button>
      </td>
    </tr>
  </tbody>
</table>
  </div>
</template>

<script>
import axios from "axios";
import EditRequest from "./edit-request.vue";

export default {
  components: {
    EditRequest,
  },
  name: "ViewRequests",
  data() {
    return {
      requests: [],
      currentRequestId: null,
    };
  },
  mounted() {
    this.getRequests();
  },
  methods: {
    getRequests() {
      let url = "http://localhost:8080/api/superfrogappearancerequests";
      if (this.selectedFilter) {
        url += `/status/${this.selectedFilter}`;
      }
      axios
      .get(url)
      .then((response) => {
        if (this.selectedFilter) {
          this.requests = response.data.data.filter(
            (request) => request.status === this.selectedFilter
          );
        } else {
          this.requests = response.data.data;
        }
  })
  .catch((error) => {
    console.log(error);
  });

    },
    approveRequest(request) {
      // TODO: Implement approve request functionality
      axios
        .put(`http://localhost:8080/api/superfrogappearancerequests/${request.requestId}/status/APPROVED`, {

        })
        .then((response) => {
          this.requests = response.data.data;
          console.log(response.data.data);
          this.getRequests();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    rejectRequest(request) {
      // TODO: Implement reject request functionality
      axios
        .put(`http://localhost:8080/api/superfrogappearancerequests/${request.requestId}/status/REJECTED`, {
        })
        .then((response) => {
          this.requests = response.data.data;
          console.log(response.data.data);
          this.getRequests();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editRequest(requestId) {
      //Store request id in local storage
      localStorage.setItem("requestId", requestId);
      //Have the parent component (AdminPage) invoke the edit request modal
      
      console.log(requestId)
      // Emit an event with the request ID as a payload
      this.currentRequestId = requestId;



    },
  },
  computed: {
    computedRequests() {
      return this.requests;
    },
  },
};
</script>
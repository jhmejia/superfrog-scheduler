<template>
  <div>
    <p id="requests"> Requests </p>
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
            <button @click="approveRequest(request.requestId)">Sign-Up</button>
            <!-- <button @click="rejectRequest(request)">Reject</button> -->
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ViewRequests",
  data() {
    return {
      requests: [],
    };
  },
  mounted() {
    this.getRequests();
  },
  methods: {
    getRequests() {

      axios
        .get("http://localhost:8080/api/superfrogappearancerequests")
        .then((response) => {
          this.requests = response.data.data;
          console.log(response.data.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getStudents() {
        axios
          .get("http://localhost:8080/api/superfrogstudents")
          .then((response) => {
            this.students = response.data.data;
            console.log (response.data.data);
          })
          .catch((error) => {
            console.log(error);
          });
      },
    approveRequest(requestId) {
      // TODO: Implement approve request functionality
      const superFrogId = localStorage.getItem('superfrogID');
      console.log("Superfrog id is ")
      console.log(superFrogId);
      axios
      .put(`http://localhost:8080/api/superfrogstudents/${superFrogId}/assign/superfrogappearancerequests/${requestId}`, {
          status: 'APPROVED',
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
  },
  computed: {
  computedRequests() {
    return this.requests.filter(request => request.status === 'APPROVED');
  },
},
};
</script>
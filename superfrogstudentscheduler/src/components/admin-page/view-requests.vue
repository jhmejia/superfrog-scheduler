<template>
  <div>
    <label for="filter">Status:</label>
    <select id="filter" v-model="selectedFilter" @change="getRequests">
      <option value="">All</option>
      <option value="APPROVED">Approved</option>
      <option value="PENDING">Pending</option>
      <option value="COMPLETED">Completed</option>
      <option value="REJECTED">Rejected</option>
    </select>
<p id="requests">Requests</p>
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
      <th>Reassignment</th>
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
      </td>
      <td>
        <select
              v-if="superfrogs.length"
              id="superfrogs-select"
              name="superfrog"
              v-model="request.superfrogId"
              @change="assignSuperFrog(request.requestId, request.superfrogId)"
            >
              <option value="">Select a SuperFrog</option>
              <option v-for="superfrog in superfrogs" :value="superfrog.id">
                {{ superfrog.name }}
              </option>
            </select>
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
      superfrogs: [],
      superfrogId: null,
    };
  },
  mounted() {
    this.getRequests();
    axios
            .get("http://localhost:8080/api/superfrogstudents/active", )
            .then((response) => {
                console.log(response.data);

                //Get first name and last name of each superfrog
                const superfrogs = response.data.data.map((superfrog) => {
                    return {
                        id: superfrog.id,
                        name: superfrog.firstName + " " + superfrog.lastName,
                    };
                });

                this.superfrogs = superfrogs;

                console.log(this.superfrogs);
            })
            .catch((error) => {
                console.error(error);
            });
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
    assignSuperFrog(requestId,superfrogId) {
      const token = localStorage.getItem("token");
      const headers = {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
      };
      axios
        .put(`http://localhost:8080/api/superfrogstudents/${superfrogId}/assign/superfrogappearancerequests/${requestId}`, {
        }, { headers})
        .then((response) => {
          console.log(response.data.data);
          this.getRequests();
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
  computed: {
    computedRequests() {
      return this.requests;
    },
  },
};
</script>
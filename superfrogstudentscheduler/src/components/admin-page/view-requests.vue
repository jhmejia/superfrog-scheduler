<template>
  <!-- Editing a request. Hidden by default, unless they click.-->

  <div v-if="currentRequestId">
    <h2> Viewing Request {{ currentRequestId }} </h2>

    <edit-request></edit-request>
    <button @click="currentRequestId = null; getRequests()">Close</button>
  </div>

  <!-- Show all requests-->
  <div>
    <label for="filter">Status:</label>
    <select id="filter" v-model=" selectedFilter " @change=" getRequests ">
      <option value="">All</option>
      <option value="APPROVED">Approved</option>
      <option value="PENDING">Pending</option>
      <option value="COMPLETED">Completed</option>
      <option value="REJECTED">Rejected</option>
      <option value="CANCELLED">Cancelled</option>
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
        <tr v-for="  request   in   displayedRequests  " :key="request.requestId">
          <td>{{ request.requestId }}</td>
          <td>{{ request.contactFirstName }} {{ request.contactLastName }}</td>
          <td>{{ request.eventDate }}</td>
          <td>{{ request.title }}</td>
          <td>{{ request.status }}</td>
          <td>{{ request.student ? request.student.firstName + ' ' + request.student.lastName : 'None' }}</td>
          <td>
            <button @click=" approveRequest(request) ">Approve</button>
            <button @click=" rejectRequest(request) ">Reject</button>
            <button @click=" editRequest(request.requestId) ">Edit/View Request</button>
            <button @click=" cancelRequest(request.requestId) ">Cancel</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button :disabled="currentPage === 1" @click="prevPage">Prev</button>
      <span>{{ currentPage }} / {{ totalPages }}</span>
      <button :disabled="currentPage === totalPages" @click="nextPage">Next</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import EditRequest from "./edit-request.vue";

//Call get requests every 5 seconds


export default {
  components: {
    EditRequest,
  },
  name: "ViewRequests",
  data() {
    return {
      requests: [],
      superfrogs: [],
      superfrogId: null,
      currentRequestId: null,
      selectedFilter: "",
      currentPage: 1,
      rowsPerPage: 5,
    };
  },
  mounted() {
    
    this.getRequests();
    axios
      .get("http://206.189.255.67:8080/api/superfrogstudents/active",)
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
      let url = "http://206.189.255.67:8080/api/superfrogappearancerequests";
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
        .put(`http://206.189.255.67:8080/api/superfrogappearancerequests/${request.requestId}/status/APPROVED`, {

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
        .put(`http://206.189.255.67:8080/api/superfrogappearancerequests/${request.requestId}/status/REJECTED`, {
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
    assignSuperFrog(requestId, superfrogId) {
      const request = this.requests.find(request => request.requestId === requestId);
      if (request.status !== 'APPROVED') {
        console.log('Cannot assign SuperFrog to request with status', request.status);
        return;
      }
      const token = localStorage.getItem("token");
      const headers = {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      };

      axios
        .put(
          `http://206.189.255.67:8080/api/superfrogstudents/${superfrogId}/assign/superfrogappearancerequests/${requestId}`,
          {},
          { headers }
        )
        .then((response) => {
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
    cancelRequest(requestId) {
      const token = localStorage.getItem("token");
      const headers = {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      };
      // TODO: Implement cancel request functionality
      const superfrogEmail = localStorage.getItem("superfrogEmail");
      let superFrogId = "";

      //Get all superfrog students
      axios
        .get("http://localhost:8080/api/superfrogstudents")
        .then((response) => {
          for (let i = 0; i < response.data.data.length; i++) {
            if (response.data.data[i].email === superfrogEmail) {
              superFrogId = response.data.data[i].id;
              console.log("Superfrog id is ");
              console.log(this.superFrogId);
              console.log("Request id is ");
              console.log(requestId);

              break;
            }
          }
          axios
            .put(`http://localhost:8080/api/superfrogappearancerequests/${requestId}/status/CANCELLED`,
              null, { headers }
            )
            .then((response) => {
              this.requests = response.data.data;
              console.log(response.data.data);
              this.getRequests();
            })
            .catch((error) => {
              console.log(error);
            });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
  },
  computed: {
    computedRequests() {
      return this.requests;
    },
    totalPages() {
      let requests = [];
      if (this.selectedFilter) {
        requests = this.requests.filter(request => request.status === this.selectedFilter);
      } else {
        requests = this.requests;
      }
      return Math.ceil(requests.length / this.rowsPerPage);
    },
    displayedRequests() {
      let requests = [];
      if (this.selectedFilter) {
        requests = this.requests.filter(request => request.status === this.selectedFilter);
      } else {
        requests = this.requests;
      }
      console.log(requests)
      const startIndex = (this.currentPage - 1) * this.rowsPerPage;
      const endIndex = startIndex + this.rowsPerPage;
      return requests.slice(startIndex, endIndex);
    },
  },
};
</script>
<style scoped>
.container {
  width: 80vw;
  height: 70vh;
}

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  text-align: center;
  padding: 8px;
}

th {
  background-color: #4CAF50;
  color: white;
  text-align: center;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
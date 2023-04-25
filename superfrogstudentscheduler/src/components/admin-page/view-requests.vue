<template>
    <div>
      <p id="requests"> Requests </p>
      <table>
        <thead>
          <tr>
            <th>Request ID</th>
            <th>Event Type</th>
            <th>Address</th>
            <th>Milage</th>
            <th>Event Date</th>
            <th>Expenses</th>
            <th>Status</th>
            
          </tr>
        </thead>
        <tbody>
          <tr v-for="request in computedRequests" :key="request.id">
            <td>{{ request.requestId }}</td>
            <td>{{ request.eventType }}</td>
            <td>{{ request.address }}</td>
            <td>{{ request.milage }}</td>
            <td>{{ request.eventDate }}</td>
            <td>{{ request.expenses }}</td>
            <td>{{ request.status }}</td>
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
            console.log (response.data.data);
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
    computed: {
      computedRequests() {
        return this.requests;
      },
    },
  };
  </script>
  
<template>
    <div>
      <p id="view-students"> Students </p>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
            <th>Account Status</th>
            
          </tr>
        </thead>
        <tbody>
          <tr v-for="student in computedStudents" :key="student.id">
            <td>{{ student.id }}</td>
            <td>{{ student.firstName }}</td>
            <td>{{ student.lastName }}</td>
            <td>{{ student.email }}</td>
            <td>{{ student.active }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    name: "ViewStudents",
    data() {
      return {
        students: [],
      };
    },
    mounted() {
      this.getStudents();
    },
    methods: {
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
    },
    computed: {
      computedStudents() {
        return this.students;
      },
    },
  };
  </script>
  
<template>
  <div class="container">
    <p id="view-students"> Students </p>
    <label>
      <input type="checkbox" v-model="showActiveOnly"> Show active students only
    </label>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Email Address</th>
          <th>Account Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in computedStudents" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.firstName }}</td>
          <td>{{ student.lastName }}</td>
          <td>{{ student.email }}</td>
          <td>{{ student.active ? "Enabled" : "Disabled" }}</td>
          <td>
            <button @click="toggleStudentStatus(student)">
              {{ student.active ? "Disable" : "Enable" }}
            </button>
          </td>
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
      showActiveOnly: false,
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
        })
        .catch((error) => {
          console.log(error);
        });
    },
    toggleStudentStatus(student) {
      const url = `http://localhost:8080/api/users/${student.email}/${student.active ? 'disable' : 'enable'}`;
      const token = localStorage.getItem('token');
      const headers = {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
      axios
        .put(url, {}, { headers })
        .then((response) => {
          console.log(response.data)
          this.getStudents();
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    computedStudents() {
      if (this.showActiveOnly) {
        return this.students.filter(student => student.active);
      } else {
        return this.students;
      }
    },
  },
};
</script>
<style scoped>
.container {
  height: 71vh;
  width: auto;
}
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

th {
  background-color: #4CAF50;
  color: white;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

</style>

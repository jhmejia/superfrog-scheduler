<template>
  <div>
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
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in computedStudents" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.firstName }}</td>
          <td>{{ student.lastName }}</td>
          <td>{{ student.email }}</td>
          <td>{{ student.active ? "Enabled" : "Disabled" }}</td>
          <!-- <td>
            <button @click="toggleStudentStatus(student)">
              {{ student.active ? "Disable" : "Enable" }}
            </button>
          </td> -->
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

export default {
  name: "ViewStudents",
  data() {
    return {
      students: [],
      showActiveOnly: false,
      currentPage: 1,
      rowsPerPage: 5,
    };
  },
  mounted() {
    this.getStudents();
  },
  methods: {
    getStudents() {
      axios
        .get("http://api.superfrogscheduler.xyz:8080/api/superfrogstudents")
        .then((response) => {
          this.students = response.data.data;
          console.log(response.data.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    toggleStudentStatus(student) {
      const url = `http://api.superfrogscheduler.xyz:8080/api/users/${student.email}/${student.active ? 'disable' : 'enable'}`;
      const token = localStorage.getItem('token');

      console.log(token);

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
    computedStudents() {
      return this.students;
    },
    totalPages() {
      let students = [];
      if (this.showActiveOnly) {
        students = this.students.filter(student => student.active);
      } else {
        students = this.students;
      }
      return Math.ceil(students.length / this.rowsPerPage);
    },
    displayedStudents() {
      let students = [];
      if (this.showActiveOnly) {
        students = this.students.filter(student => student.active);
      } else {
        students = this.students;
      }
      const startIndex = (this.currentPage - 1) * this.rowsPerPage;
      const endIndex = startIndex + this.rowsPerPage;
      return students.slice(startIndex, endIndex);
    },
  },
};
</script>
<style scoped>
table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
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
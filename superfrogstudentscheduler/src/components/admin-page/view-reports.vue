<template>
    <div class="view-reports">
        <h2>View Reports</h2>

        <div class="input-container">
            <label for="student-id">Student ID:</label>
            <input id="student-id" v-model="studentId" type="text" placeholder="Enter student ID" />

            <button @click="generateReport" class="generate-report-btn">
                Generate Report
            </button>
        </div>

        <div v-if="completedRequests !== null" class="result-container">
            <h3>Report Results:</h3>
            <p>Completed Requests: {{ completedRequests }}</p>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "ViewReports",

    data() {
        return {
            studentId: "",
            completedRequests: null,
        };
    },
    methods: {

        async generateReport() {
            const token = localStorage.getItem("token");
            const headers = {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
            try {
                const response = await axios.get(`http://localhost:8080/api/performance/superfrogstudents/${this.studentId}`, {headers});
                this.completedRequests = response.data.data;
                console.log("Report generated:", response.data.data);
            } catch (error) {
                console.error("Error generating report:", error);
            }
        },
    },
};
</script>

<style scoped>
.view-reports {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.input-container {
    display: flex;
    align-items: center;
}

input {
    margin-left: 10px;
    margin-right: 20px;
}

.generate-report-btn {
    background-color: #42b983;
    border: none;
    border-radius: 5px;
    color: white;
    cursor: pointer;
    font-size: 16px;
    padding: 10px 20px;
    transition: background-color 0.3s;
}

.generate-report-btn:hover {
    background-color: #2c9f77;
}

.result-container {
    margin-top: 20px;
}
</style>

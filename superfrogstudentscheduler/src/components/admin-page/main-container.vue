<template>
    <div class="main-box">
        <h1>{{ section }}</h1>
        
        <div class="text" v-if="section === 'add-student'">
            <!-- Include vue container for form to add a student (add-student.vue)-->
            <add-student></add-student>
        </div>
        <div class="text" v-if="section === 'view-students'">
            <p id="all-student">All Students</p>
            <view-students></view-students>
        </div>
        <div class="text" v-if="section === 'add-request'">
            <!-- Add request form here -->
            <p id="add-request">Add Request</p>
            <add-request></add-request>
        </div>
        <div class="text" v-if="section === 'view-requests'">

            
            <!-- if currentRequestId is null, show view-requests -->
            <!-- if currentRequestId is not null, show edit-request -->

            
            
            <view-requests v-if="currentRequestEdit === null"></view-requests>
            <edit-request v-else></edit-request>
            <edit-request v-else @editRequest="editRequest"></edit-request>




            
            <!-- Include vue container for viewing requests (view-requests.vue)-->
        </div>
        <div class="text" v-if="section === 'view-calendar'">
            <!-- Display calendar here -->
        </div>
        <div class="text" v-if="section === 'view-reports'">
            <!-- Display reports here -->
        </div>
    </div>
</template>

<script>
import axios from "axios";
import AddStudent from "./add-student.vue";
import ViewRequests from "./view-requests.vue";
import ViewStudents from "./view-students.vue";
import AddRequest from "./add-request.vue";
import EditRequest from "./edit-request.vue";

export default {
    props: {
        section: {
            type: String,
            default: "students",
        },
    },
    components: {
        AddStudent,
        ViewRequests,
        ViewStudents,
        AddRequest,
        EditRequest,
    },
    data() {
        return {
            students: [],
        };
    },
    mounted() {
        this.currentRequestEdit = null;
        
    },
    methods: {
        editRequest(requestEdit) {
            console.log("Editing request: " + requestEdit)
            this.currentRequestEdit = requestEdit;

            
        },
        goBack() {
            console.log("Going back")
            this.currentRequestEdit = null;
            console.log("Current request id: " + this.currentRequestEdit);
        },
        
    },
};
</script>

<style scoped>
.text {
    color: black;
}
.h1 {
    color: black;
}

table {
    border-collapse: collapse;
    width: 100%;
    color: #588c7e;
    font-family: monospace;
    font-size: 25px;
    text-align: left;
}

th {
    background-color: #588c7e;
    color: white;
    border: 1px solid black;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}
</style>

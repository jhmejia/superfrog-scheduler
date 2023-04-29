<template>
    <div class="main-box">
        <h1>{{ section }}</h1>
        <div class="text" v-if="section === 'add-student'">

            <!-- Include vue container for form to add a student (add-student.vue)-->
            <add-student></add-student>

        </div>
        <div class="text" v-if="section === 'view-students'">
            <p id="all-student"> All Students </p>
            <view-students></view-students>
        </div>
        <div class="text" v-if="section === 'add-request'">
            <!-- Add request form here -->
            <p id="add-request"> Add Request </p>
            <add-request></add-request>
        </div>
        <div class="text" v-if="section === 'view-requests'">
            <!-- Display all requests here -->

            <!-- Include vue container for viewing requests (view-requests.vue)-->
            <view-requests></view-requests>
        </div>
        <div class="text" v-if="section === 'view-calendar'">
            <!-- Display calendar here -->
            Yo, I need to add the backend to view the calendar, can't do it
            alone so I'm askin' for assistance.
        </div>
        <div class="text" v-if="section === 'view-reports'">
            <!-- Display reports here -->
            Yo, I need to add the backend to view the reports, can't do it alone
            so I'm askin' for assistance.
        </div>
    </div>
</template>

<script>
import axios from "axios";
import AddStudent from "./add-student.vue";
import ViewRequests from "./view-requests.vue";
import ViewStudents from "./view-students.vue";
import AddRequest from "./add-request.vue";

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
    },
    data() {
        return {
            students: [],
        };
    },
    mounted() {
        
        
    },
    methods: {
        
    },
};
</script>

<style>
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

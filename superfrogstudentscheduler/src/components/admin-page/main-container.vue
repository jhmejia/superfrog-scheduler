<template>
    <div class="main-box">
        <h1>{{ section }}</h1>
        <div class="text" v-if="section === 'add-student'">
            <!-- add student form here -->
            Add dat backend to add a student. Yo

            <!-- Include vue container for form to add a student (add-student.vue)-->
            <add-student></add-student>

        </div>
        <div class="text" v-if="section === 'view-students'">
            <p id="all-student"> All Students </p>
            <view-students></view-students>
        </div>
        <div class="text" v-if="section === 'add-request'">
            <!-- Add request form here -->
            Add dat backend to add a request. Yo
        </div>
        <div class="text" v-if="section === 'view-requests'">
            <!-- Display all requests here -->
            Yo, I need to add the backend to view all requests, can't do it
            alone so I'm askin' for assistance.

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
        ViewStudents
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

<style scoped>
.text {
    color: black;
}
.h1 {
    color: black;
}
</style>

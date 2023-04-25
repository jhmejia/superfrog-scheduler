<template>
    <div class="main-box">
        <h1>{{ section }}</h1>
        <div v-if="section === 'add-student'">
            <!-- add student form here -->
            Add dat backend to add a student. Yo
        </div>
        <div v-if="section === 'view-students'">
            <p id="all-student"> All Students </p>
        </div>
        <div v-if="section === 'add-request'">
            <!-- Add request form here -->
            Add dat backend to add a request. Yo
        </div>
        <div v-if="section === 'view-requests'">
            <!-- Display all requests here -->
            Yo, I need to add the backend to view all requests, can't do it
            alone so I'm askin' for assistance.
        </div>
        <div v-if="section === 'view-calendar'">
            <!-- Display calendar here -->
            Yo, I need to add the backend to view the calendar, can't do it
            alone so I'm askin' for assistance.
        </div>
        <div v-if="section === 'view-reports'">
            <!-- Display reports here -->
            Yo, I need to add the backend to view the reports, can't do it alone
            so I'm askin' for assistance.
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    props: {
        section: {
            type: String,
            default: "students",
        },
    },
    data() {
        return {
            students: [],
        };
    },
    mounted() {
        if (this.section === "view-students") {
            this.getStudents();
        }
        
    },
    methods: {
        getStudents() {
            const base_url = "http://localhost:5000/api/v1/students";
            axios
                .get(base_url)
                .then((response) => {
                    console.log(response.data);
                    this.students = response.data;
                })
                .catch((error) => {
                    console.error(error);
                    this.students = "Can't find students";
                });
        },
    },
};
</script>
<template>
    <form @submit.prevent="editRequest">
        <!-- eventtype, address, milage, eventDate, startTime, endTime -->
        <label for="eventType">Event Type:</label><br />
        <input
            type="text"
            id="eventType"
            name="eventType"
            v-model="eventType"
        /><br />
        <label for="address">Address:</label><br />
        <input
            type="text"
            id="address"
            name="address"
            v-model="address"
        /><br />
        <label for="milage">Mileage:</label><br />
        <input type="text" id="milage" name="milage" v-model="milage" /><br />
        <label for="eventDate">Event Date:</label><br />
        <input
            type="text"
            id="eventDate"
            name="eventDate"
            v-model="eventDate"
        /><br />
        <label for="startTime">Start Time:</label><br />
        <input
            type="text"
            id="startTime"
            name="startTime"
            v-model="startTime"
        /><br />
        <label for="endTime">End Time:</label><br />
        <input
            type="text"
            id="endTime"
            name="endTime"
            v-model="endTime"
        /><br />
        <label for="status">Status:</label><br />
        <select id="status" name="status" v-model="status">
            <option value="cancelled">Cancelled</option>
            <option value="approved">Approved</option>
            <option value="completed">Completed</option>
            <option value="rejected">Rejected</option></select
        ><br />
        <label for="contactFirstName">Contact First Name:</label><br />
        <input
            type="text"
            id="contactFirstName"
            name="contactFirstName"
            v-model="contactFirstName"
        /><br />
        <label for="contactLastName">Contact Last Name:</label><br />
        <input
            type="text"
            id="contactLastName"
            name="contactLastName"
            v-model="contactLastName"
        /><br />
        <label for="phoneNumber">Phone Number:</label><br />
        <input
            type="text"
            id="phoneNumber"
            name="phoneNumber"
            v-model="phoneNumber"
        /><br />
        <label for="email">Email:</label><br />
        <input type="text" id="email" name="email" v-model="email" /><br />
        <label for="title">Title:</label><br />
        <input type="text" id="title" name="title" v-model="title" /><br />
        <label for="nameOfOrg">Name of Organization:</label><br />
        <input
            type="text"
            id="nameOfOrg"
            name="nameOfOrg"
            v-model="nameOfOrg"
        /><br />
        <label for="description">Description:</label><br />
        <input
            type="text"
            id="description"
            name="description"
            v-model="description"
        /><br />
        <label for="specialInstructions">Special Instructions:</label><br />
        <input
            type="text"
            id="specialInstructions"
            name="specialInstructions"
            v-model="specialInstructions"
        /><br />
        <label for="outsideOrgs">Outside Organizations:</label><br />
        <input
            type="text"
            id="outsideOrgs"
            name="outsideOrgs"
            v-model="outsideOrgs"
        /><br />
        <label for="expenses">Expenses:</label><br />
        <input type="text" id="expenses" name="expenses" v-model="expenses" />
        <br />

        <label for="superfrogs-select">Select an Active SuperFrog:</label>

        <select
            v-if="superfrogs.length"
            id="superfrogs-select"
            name="superfrog"
            v-model="superfrogId"
        >
            <option value="None">Select a SuperFrog</option>
            <option v-for="superfrog in superfrogs" :value="superfrog.id">
                {{ superfrog.name }}
            </option>
        </select>

        <input type="submit" value="Submit" />
    </form>

    <p v-if="successMessage">{{ successMessage }}</p>
    <p v-if="superfrogSuccessMessage">{{ superfrogSuccessMessage }}</p>
</template>

<script>
import axios from "axios";

export default {
    name: "EditRequest",
    data() {
        return {
            eventType: "",
            address: "",
            milage: "",
            eventDate: "",
            startTime: "",
            endTime: "",
            status: "",
            contactFirstName: "",
            contactLastName: "",
            phoneNumber: "",
            email: "",
            title: "",
            nameOfOrg: "",
            description: "",
            specialInstructions: "",
            outsideOrgs: "",
            expenses: "",
            successMessage: null,

            superfrogs: [],
            superfrogId: null,
            requestId: null,
        };
    },
    mounted() {
        console.log("EditRequest.vue mounted");

        //Load the request ID from local storage
        this.requestId = localStorage.getItem("requestId");

        //Get the request by ID

        axios
            .get(
                `http://localhost:8080/api/superfrogappearancerequests/${this.requestId}`
            )
            .then((response) => {
                console.log(response.data);

                this.eventType = response.data.data.eventType;
                this.address = response.data.data.address;
                this.milage = response.data.data.milage;
                this.eventDate = response.data.data.eventDate;
                this.startTime = response.data.data.startTime;
                this.endTime = response.data.data.endTime;
                this.status = response.data.data.status;
                this.contactFirstName = response.data.data.contactFirstName;
                this.contactLastName = response.data.data.contactLastName;
                this.phoneNumber = response.data.data.phoneNumber;
                this.email = response.data.data.email;
                this.title = response.data.data.title;
                this.nameOfOrg = response.data.data.nameOfOrg;
                this.description = response.data.data.description;
                this.specialInstructions =
                    response.data.data.specialInstructions;
                this.outsideOrgs = response.data.data.outsideOrgs;
                this.expenses = response.data.data.expenses;
            })
            .catch((error) => {
                console.error(error);
            });

        axios
            .get("http://localhost:8080/api/superfrogstudents/active")
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
        editRequest() {
            const token = localStorage.getItem("token");
            const headers = {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            };

            axios
                .put(
                    `http://localhost:8080/api/superfrogappearancerequests/${this.requestId}`,
                    {
                        eventType: this.eventType,
                        address: this.address,
                        milage: this.milage,
                        eventDate: this.eventDate,
                        startTime: this.startTime,
                        endTime: this.endTime,
                        status: this.status,
                        contactFirstName: this.contactFirstName,
                        contactLastName: this.contactLastName,
                        phoneNumber: this.phoneNumber,
                        email: this.email,
                        title: this.title,
                        nameOfOrg: this.nameOfOrg,
                        description: this.description,
                        specialInstructions: this.specialInstructions,
                        outsideOrgs: this.outsideOrgs,
                        expenses: this.expenses,
                    },
                    { headers }
                )
                .then((response) => {
                    console.log(response.data);
                    this.successMessage = "Request edited successfully!";
                    this.requestId = response.data.data.requestId;
                    console.log("Request id is ");
                    console.log(this.requestId);
                })
                .catch((error) => {
                    console.log(error);
                    this.successMessage =
                        "Request not edited successfully- please try again";
                });

            //Assign superfrog to request (if selected)

            if (this.superfrogId) {
                axios
                    .put(
                        `http://localhost:8080/api/superfrogstudents/${this.superfrogId}/assign/superfrogappearancerequests/${this.requestId}`,

                        {
                            superfrogId: this.superfrogId,
                            requestId: this.requestId,
                        },
                        { headers }
                    )
                    .then((response) => {
                        console.log(response.data);
                        this.superfrogSuccessMessage =
                            "SuperFrog assigned successfully!";
                    })
                    .catch((error) => {
                        console.log(error);
                        this.superfrogSuccessMessage =
                            "SuperFrog not assigned successfully- please try again";
                    });
            }

            // call get requests method in view requests component
            this.$emit("getRequests");
        },
    },
};
</script>

<style>
form {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-gap: 2px;
    border-radius: 10px;
    background-color: #f9f9f9;
    padding: 2px;
}

label {
    font-size: 1.2em;
}

input[type="text"] {
    border-radius: 5px;
    padding: 10px;
    font-size: 1.2em;
    border: 1px solid #ccc;
}

input[type="submit"] {
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px;
    font-size: 1.2em;
    cursor: pointer;
}

input[type="cancel"] {
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px;
    font-size: 1.2em;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #3e8e41;
}

input[type="cancel"]:hover {
    background-color: #3e8e41;
}
</style>

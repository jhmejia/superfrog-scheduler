<template>
    <div>
        <p id="requests">Requests</p>
        <table>
            <thead>
                <tr>
                    <th>Request ID</th>
                    <th>Customer Name</th>
                    <th>Event Date</th>
                    <th>Event Title</th>
                    <th>Request Status</th>
                    <th>Assigned SuperFrog</th>
                    <th>Action</th>
                    
                </tr>
            </thead>
            <tbody>
                <tr v-for="request in computedRequests" :key="request.id">
                    <td>{{ request.requestId }}</td>
                    <td>
                        {{ request.contactFirstName }}
                        {{ request.contactLastName }}
                    </td>
                    <td>{{ request.eventDate }}</td>
                    <td>{{ request.title }}</td>
                    <td>{{ request.status }}</td>
                    <td>
                        {{
                            request.student
                                ? request.student.firstName +
                                  " " +
                                  request.student.lastName
                                : "None"
                        }}
                    </td>
                    <td>
                        <button @click="approveRequest(request.requestId)">
                            Sign-Up
                        </button>
                        <button v-if="request.status === 'APPROVED'" @click="completeRequest(request.requestId)">
                            Completed
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
                .get("http://api.superfrogscheduler.xyz:8080/api/superfrogappearancerequests")
                .then((response) => {
                    this.requests = response.data.data;
                    console.log(response.data.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
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
        approveRequest(requestId) {
            const token = localStorage.getItem("token");
            const headers = {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            };
            // TODO: Implement approve request functionality
            const superfrogEmail = localStorage.getItem("superfrogEmail");
            let superFrogId = "";

            //Get all superfrog students
            axios
                .get("http://api.superfrogscheduler.xyz:8080/api/superfrogstudents")
                .then((response) => {
                    for (let i = 0; i < response.data.data.length; i++) {
                        if (response.data.data[i].email === superfrogEmail) {
                            superFrogId = response.data.data[i].id;
                            console.log("Superfrog id is ");
                            console.log(this.superFrogId);
                            console.log("Request id is ");
                            console.log(requestId);

                            break;
                        }
                    }

                    axios
                        .put(
                            `http://api.superfrogscheduler.xyz:8080/api/superfrogstudents/${superFrogId}/assign/superfrogappearancerequests/${requestId}`,
                            {
                                status: "APPROVED",
                            },  { headers }
                        )
                        .then((response) => {
                            this.requests = response.data.data;
                            console.log(response.data.data);
                            this.getRequests();
                        })
                        .catch((error) => {
                            console.log(error);
                        });
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        completeRequest(requestId) {
        const token = localStorage.getItem("token");
        const headers = {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        };
        // TODO: Implement complete request functionality
        const superfrogEmail = localStorage.getItem("superfrogEmail");
        let superFrogId = "";

        //Get all superfrog students
        axios
            .get("http://api.superfrogscheduler.xyz:8080/api/superfrogstudents")
            .then((response) => {
                for (let i = 0; i < response.data.data.length; i++) {
                    if (response.data.data[i].email === superfrogEmail) {
                        superFrogId = response.data.data[i].id;
                        console.log("Superfrog id is ");
                        console.log(this.superFrogId);
                        console.log("Request id is ");
                        console.log(requestId);

                        break;
                    }
                }

                axios
                    .put(
                        `http://api.superfrogscheduler.xyz:8080/api/superfrogappearancerequests/${requestId}/status/COMPLETED`,
                        null,  { headers }
                    )
                    .then((response) => {
                        this.requests = response.data.data;
                        console.log(response.data.data);
                        this.getRequests();
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            })
            .catch((error) => {
                console.log(error);
            });
    },
    },

    computed: {
        computedRequests() {
            return this.requests.filter(
                (request) => request.status === "APPROVED"
            );
        },
    },
};
</script>
<style scoped>
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
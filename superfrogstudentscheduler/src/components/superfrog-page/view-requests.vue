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
                        <button v-if="request.status === 'APPROVED'" @click="cancelRequest(request.requestId)">
                            Cancel
                        </button>

                    </td>

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
    name: "ViewRequests",
    data() {
        return {
            requests: [],
            superfrogs: [],
            superfrogId: null,
            currentRequestId: null,
            currentPage: 1,
            rowsPerPage: 5,
        };
    },
    mounted() {

        this.getRequests();
        axios
            .get("http://localhost:8080/api/superfrogstudents/active",)
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
        getRequests() {
            let url = "http://localhost:8080/api/superfrogappearancerequests";
            if (this.selectedFilter) {
                url += `/status/${this.selectedFilter}`;
            }
            axios
                .get(url)
                .then((response) => {
                    if (this.selectedFilter) {
                        this.requests = response.data.data.filter(
                            (request) => request.status === this.selectedFilter
                        );
                    } else {
                        this.requests = response.data.data;
                    }
                })
                .catch((error) => {
                    console.log(error);
                });

        },
        getStudents() {
            axios
                .get("http://localhost:8080/api/superfrogstudents")
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
                .get("http://localhost:8080/api/superfrogstudents")
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
                            `http://localhost:8080/api/superfrogstudents/${superfrogId}/assign/superfrogappearancerequests/${requestId}`,
                            {},
                            { headers }
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
                .get("http://localhost:8080/api/superfrogstudents")
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
                            `http://localhost:8080/api/superfrogappearancerequests/${requestId}/status/COMPLETED`,
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
        cancelRequest(requestId) {
            const token = localStorage.getItem("token");
            const headers = {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
            };
            // TODO: Implement cancel request functionality
            const superfrogEmail = localStorage.getItem("superfrogEmail");
            let superFrogId = "";

            //Get all superfrog students
            axios
                .get("http://localhost:8080/api/superfrogstudents")
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
                        .put(`http://localhost:8080/api/superfrogappearancerequests/${requestId}/status/CANCELLED`,
                            null, { headers }
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
        computedRequests() {
            return this.requests.filter(
                (request) => request.status === "APPROVED"
            );
        },
        totalPages() {
            let requests = [];
            if (this.selectedFilter) {
                requests = this.requests.filter(request => request.status === this.selectedFilter);
            } else {
                requests = this.requests;
            }
            return Math.ceil(requests.length / this.rowsPerPage);
        },
        displayedRequests() {
            let requests = [];
            if (this.selectedFilter) {
                requests = this.requests.filter(request => request.status === this.selectedFilter);
            } else {
                requests = this.requests;
            }
            console.log(requests)
            const startIndex = (this.currentPage - 1) * this.rowsPerPage;
            const endIndex = startIndex + this.rowsPerPage;
            return requests.slice(startIndex, endIndex);
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
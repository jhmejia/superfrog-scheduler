<template>
    <div>
        <h1>Edit Superfrog Profile</h1>
        <table>
            <tbody>
                <tr>
                    <td>
                        <label for="firstName">First Name:</label>
                    </td>
                    <td>
                        <input type="text" id="firstName" v-model="superfrog.firstName" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="lastName">Last Name:</label>
                    </td>
                    <td>
                        <input type="text" id="lastName" v-model="superfrog.lastName" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="email">Email:</label>
                    </td>
                    <td>
                        <input type="email" id="email" v-model="superfrog.email" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="phoneNumber">Phone Number:</label>
                    </td>
                    <td>
                        <input type="tel" id="phoneNumber" v-model="superfrog.phoneNumber" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="status">Status:</label>
                    </td>
                    <td>
                        <select id="status" v-model="superfrog.status" required>
                            <option value="ACTIVE">Active</option>
                            <option value="INACTIVE">Inactive</option>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
        <button type="submit" @click="updateSuperfrog()">Save Changes</button>
    </div>
</template>
  
<script>
import axios from "axios";

export default {
    name: "EditSuperfrogProfile",
    data() {
        return {
            superfrog: {
                firstName: "",
                lastName: "",
                email: "",
                phoneNumber: "",
                status: ""
            }
        };
    },
    mounted() {
        this.getSuperfrog();
    },
    methods: {
        getSuperfrog() {
            const id = this.$route.params.id; // get Superfrog ID from route parameters
            axios
                .get(`http://localhost:8080/api/superfrogstudents/${id}`)
                .then((response) => {
                    this.superfrog = response.data.data;
                    console.log(response.data.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        updateSuperfrog() {
        const token = localStorage.getItem("token");
        const headers = {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
        };

        const superfrogId = this.superfrog.id; // assuming you have the ID of the Superfrog

        axios
            .put(
                `http://localhost:8080/api/superfrogstudents/${superfrogId}`,
                this.superfrog,
                { headers }
            )
            .then((response) => {
                console.log(response.data);
                // handle success
            })
            .catch((error) => {
                console.log(error);
                // handle error
            });
        },
    },
};
</script>
  
<style scoped>
table {
    border-collapse: collapse;
    width: 100%;
    margin-bottom: 20px;
}

td {
    padding: 10px;
    border: 1px solid #ddd;
}

td:first-child {
    width: 30%;
}

input,
select {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
}

button {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
</style>  
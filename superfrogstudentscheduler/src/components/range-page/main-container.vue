<template>
    <div class="pricing">
        <header>
            <div class="logo">SuperFrog Scheduler</div>

            <nav>
                <ul>
                    <li><button v-on:click="goToHome">Home</button></li>
                    <li><button v-on:click="goToPricing">Pricing</button></li>
                    <li><button v-on:click="goToRequest">Request</button></li>
                    <li>
                        <button v-on:click="goToModifyRequest">
                            Modify a request
                        </button>
                    </li>
                </ul>
            </nav>
        </header>
        <main>
            <h1>Range Availability</h1>
            <p>
                Requests can be placed within 50 miles of the Amon G Carter
                stadium. Check the map below for the radius around the stadium.
            </p>

            <div id="map">
                <Map></Map>
            </div>
    
            <p>
                Place your request within the 50-mile range to schedule an
                appearance by our mascot.
            </p>
        </main>
        <footer>
            <p style="text-align: center">
                Contact us at
                <a href="mailto:info@superfrog.com">info@superfrog.com</a> or
                call 555-1234 for more information.
            </p>
        </footer>
    </div>
</template>

<script>

import Map from "./map.vue";


export default {
    name: "RangePage",
    components: {
        Map
    },
    data() {
        return {};
    },
    methods: {
        goToHome() {
            this.$router.push("/");
        },
        goToPricing() {
            this.$router.push("/pricing");
        },
        goToRequest() {
            this.$router.push("/request");
        },
        goToModifyRequest() {
            this.$router.push("/modify-request");
        },
    },
};
</script>



<style scoped>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    /*background-color: #fff;*/
}

.pricing {
    background-color: #fff;
    /* text color black*/
    color: #000;
}

header {
    background-color: #060808;
    color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
}

.logo {
    font-size: 1.5rem;
    font-weight: bold;
}

nav ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
}

nav li {
    margin-left: 20px;
}

nav a {
    color: #fff;
    text-decoration: none;
}

main {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
}

h1 {
    margin-top: 0;
}

ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
table {
    border-collapse: collapse;
}

table th,
table td {
    border: 1px solid black;
    padding: 8px;
}
</style> 


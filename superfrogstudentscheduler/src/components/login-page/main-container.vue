<template>
    <div>
        <form>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" /><br /><br />
            <label for="password">Password:</label>
            <input type="text" id="password" name="password" /><br /><br />
        </form>

        <button type="button" class="btn btn-primary">
            Login as SuperFrog
        </button>
        <button type="button" class="btn btn-primary">
            Login as Spirit Director
        </button>

        <div class="home">  
            <button type="button" class="btn btn-secondary" v-on:click="goHome">
                Go Back
            </button>
        </div>
    </div>
</template>

<script>
export default {
    name: "LoginPage",
    data() {
        return {
            username: "",
            password: "",
        };
    },
    methods: {
        goHome() {
            this.$router.push("/");
        },
    },
    
};
</script>

<style scoped>
form {
    margin: 0 auto;
    width: 400px;
}
</style>
<template>

    <form>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">Password:</label>
        <input type="text" id="password" name="password"><br><br>
    </form>

    <button type="button" class="btn btn-primary">Login as SuperFrog</button>
    <button type="button" class="btn btn-primary">Login as Spirit Director</button>

</template>

<script>
    export default {
        name: 'LoginPage',
        data() {
            return {
                username: '',
                password: ''
            }
        }
    }
</script>

<style scoped>
    form {
        margin: 0 auto;
        width: 400px;
    }
</style>



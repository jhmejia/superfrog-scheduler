<template>
    <form> <!-- First name last name, id-->
        <label for="fname">First name:</label><br>
        <input type="text" id="fname" name="fname"><br>
        <label for="lname">Last name:</label><br>
        <input type="text" id="lname" name="lname"><br>
        <label for="id">ID:</label><br>
        <input type="text" id="id" name="id"><br>
        <input type="submit" value="Submit">
    </form>

</template>

<script>

export default {
    name: "AddStudent",
    data() {
        return {
            fname: "",
            lname: "",
            id: "",
        };
    },
    methods: {
        addStudent() {
            axios.post("localhost:8080/api/superfrogstudents/1001", {
                 fname: this.fname,
                 lname: this.lname,
                 id: this.id,
             });
            console.log("Student added");
        },
    },
}

</script>
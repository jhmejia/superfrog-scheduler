<template>
    <div class="login-page">
        <div class="landing-page">
            <div class="profile-image-admin"></div>
            <h1 class="heading-admin">
                SuperFrog Scheduler Login Page
            </h1> <br>
            <!-- <h2>
                Admin Login 
            </h2> -->

        </div>
        <h2>Enter your SuperFrog/Spirit Director Username and Password</h2>
        <form>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" v-model="username" />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" v-model="password" />
            </div>
        </form>

        <div class="login-buttons">
            <button type="button" @click="loginAsSuperFrog()" class="btn btn-primary">
                Login as SuperFrog
            </button>
            <button type="button" @click="loginAsSpiritDirector()" class="btn btn-secondary">
                Login as Spirit Director
            </button>
        </div>
        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>

        <router-link to="/">
        <button class="button is-primary home-button">Home</button>
      </router-link>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    name: "LoginPage",
    data() {
        return {
            username: "",
            password: "",
            errorMessage: null,
        };
    },
    methods: {
        async loginAsSpiritDirector() {
            try {
                console.log(this.username);
                console.log(this.password);
                const basicAuth = 'Basic ' + btoa(this.username + ':' + this.password);
                console.log(basicAuth);
                const response = await axios.post('http://localhost:8080/api/users/login', {}, {
                    headers: {
                        Authorization: basicAuth
                    }
                });
                console.log(response.data);
                const token = response.data.data.token;
                localStorage.setItem('token',token);
                console.log(token);
                // redirect to a new page after successful login
                this.$router.push('/admin');
            } catch (error) {
                console.error(error);
                this.errorMessage = error.response.data.message;
            }
        },

        async loginAsSuperFrog() {
            try {
                console.log(this.username);
                console.log(this.password);
                const basicAuth = 'Basic ' + btoa(this.username + ':' + this.password);
                console.log(basicAuth);
                const response = await axios.post('http://localhost:8080/api/users/login', {}, {
                    headers: {
                        Authorization: basicAuth
                    }
                });
                console.log(response.data);
                const token = response.data.data.token;
                const superfrogID = response.data.data.userInfo.id;
                localStorage.setItem('token',token);
                console.log(token);
                localStorage.setItem('superfrogID', superfrogID);
                console.log(superfrogID);
                // redirect to a new page after successful login
                this.$router.push('/superfrog');
            } catch (error) {
                console.error(error);
                this.errorMessage = error.response.data.message;
            }
        },
        goHome() {
            this.$router.push("/");
        },
    },

};
</script>

<style scoped>
.login-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    width: 400px;
    margin-bottom: 20px;
}

.form-group {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    bottom: 500px;
}

label {
    font-weight: bold;
    color: black;
}

input[type="text"],
input[type="password"] {
    padding: 10px;
    margin: 10px 0;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 70%;
    font-size: 16px;
}



button {
    font-size: 20px;
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
    border: none;
    background-color: white;
    color: black;
    cursor: pointer;
    border-radius: 40px;
    display: inline-block;
    margin-bottom: 200px;
}

.btn-primary {
    background-color: white;
    width: 200px;
}

/* .btn-secondary {
    background-color: white;
} */

.btn-primary:hover,
.btn-secondary:hover {
    opacity: 0.8;
}

.btn-secondary {
    margin-right: 0;
}

.login-buttons {
  display: flex;
  justify-content: center;
  margin-bottom: 20px; /* add margin to bottom to create space between the two button groups */
}

.home {
  display: flex;
  justify-content: center;
}

.profile-image-admin {
    position: absolute;
    top: 20px;
    right: 20px;
    border-radius: 50%;
    width: 250px;
    height: 250px;
    background-image: url("https://gofrogs.com/common/controls/image_handler.aspx?thumb_id=12&image_path=/images/2018/9/26/SuperFrogSITE_crop_71.jpg");
    background-size: cover;
    background-position: center;
}

.heading-admin {
    font-family: "Times New Roman", Times, serif;
    color: white;
    position: absolute;
    top: 20px;
    left: 20px;
    font-size: 50px;
    font-weight: 2000px;
}
button.button.is-primary {
  width: 120px;
}

</style>

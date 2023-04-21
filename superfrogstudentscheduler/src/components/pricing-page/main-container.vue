<template>
  <div class="pricing">
    <header>
      <div class="logo">SuperFrog</div>
      <nav>
        <ul>
          <li><button v-on:click="goToHome">Home</button></li>
          <li><button v-on:click="goToRange">Range</button></li>
          <li><button v-on:click="goToRequest">Request</button></li>
          <li><button v-on:click="goToModifyRequest">Modify a request</button></li>
        </ul>
      </nav>
    </header>
    <main>
      <h1>Pricing</h1>
      <p>Our mascot service offers the following pricing options:</p>
      <ul>
        <li>
          <h2>Bronze</h2>
          <p class="price">$50/hour</p>
          <ul>
            <li>1 mascot performer</li>
            <li>Up to 2 hours of service</li>
            <li>Basic costume options</li>
          </ul>
        </li>
        <li>
          <h2>Silver</h2>
          <p class="price">$75/hour</p>
          <ul>
            <li>1 mascot performer</li>
            <li>Up to 3 hours of service</li>
            <li>Advanced costume options</li>
            <li>Photo opportunities with guests</li>
          </ul>
        </li>
        <li>
          <h2>Gold</h2>
          <p class="price">$100/hour</p>
          <ul>
            <li>2 mascot performers</li>
            <li>Up to 4 hours of service</li>
            <li>Premium costume options</li>
            <li>Photo opportunities with guests</li>
            <li>Mascot performance and entertainment</li>
          </ul>
        </li>
      </ul>
    </main>
    <footer>
      <p>
        Contact us at
        <a href="mailto:info@superfrog.com">info@superfrog.com</a> or call
        555-1234 for more information.
      </p>
    </footer>
  </div>
</template>


<script>

    export default {
      name: 'PricingPage',
      components: {
      },
      data() {
        return {
        }
      },
      methods: {
        goToHome() {
              this.$router.push('/')
          },
          goToRange() {
              this.$router.push('/range')
          },
          goToRequest() {
              this.$router.push('/request')
          },
          goToModifyRequest() {
              this.$router.push('/modify-request')
          }
      }
    }

</script>


<style> 


body {
  font-family: Arial, sans-serif;
  margin: 0;
  /*background-color: #fff;*/
}

.pricing {
  background-color: #fff;
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

</style>

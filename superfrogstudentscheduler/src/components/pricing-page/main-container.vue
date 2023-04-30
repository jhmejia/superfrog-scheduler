<template>
  <div class="pricing">
    <header>

      <div class="logo">SuperFrog Scheduler</div>

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

      <h1>Spirit Appearance Fees</h1>
      <p>GENERAL APPEARANCES – Rates Per Appearance (All events outside of a TCU Athletic match are paid appearances.
        Mascot times will be determined by type of event, please contact office for more details.)</p>
      <table>
        <thead>
          <tr>
            <th>Package</th>
            <th>Price</th>
            <th>Performer(s)</th>
            <th>Hours of Service</th>
            <th>Costume Options</th>
            <th>Photo Opportunities</th>
            <th>Performance & Entertainment</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td style="text-align:center;">Bronze</td>
            <td style="text-align:center;">$50/hour</td>
            <td style="text-align:center;">1</td>
            <td style="text-align:center;">Up to 2 hours</td>
            <td style="text-align:center;">Basic</td>
            <td style="text-align:center;">No</td>
            <td style="text-align:center;">No</td>
          </tr>
          <tr>
            <td style="text-align:center;">Silver</td>
            <td style="text-align:center;">$75/hour</td>
            <td style="text-align:center;">1</td>
            <td style="text-align:center;">Up to 3 hours</td>
            <td style="text-align:center;">Advanced</td>
            <td style="text-align:center;">Yes</td>
            <td style="text-align:center;">No</td>
          </tr>
          <tr>
            <td style="text-align:center;">Gold</td>
            <td style="text-align:center;">$100/hour</td>
            <td style="text-align:center;">2</td>
            <td style="text-align:center;">Up to 4 hours</td>
            <td style="text-align:center;">Premium</td>
            <td style="text-align:center;">Yes</td>
            <td style="text-align:center;">Yes</td>
          </tr>
        </tbody>
      </table>
    </main>
    <footer>
      <p style="text-align:center;">

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
  margin: auto;
}


table th, table td {
  border: 1px solid black;
  padding: 8px;
}

</style>


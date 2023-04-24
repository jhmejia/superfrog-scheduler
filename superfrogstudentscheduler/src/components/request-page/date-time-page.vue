<template class="main">
  <Datepicker
    
    v-model="selectedDate"
    lang="en"
  />
  
</template>
  
  <script>

  import 'vue-datepicker-ui/lib/vuedatepickerui.css';
  import VueDatepickerUi from 'vue-datepicker-ui';

  export default {
    name: 'DateTimePage',
    data() {
      return {
        selectedDate: [
        new Date()
          ],
        startTime: '',
        endTime: '',
      }
    },
    props: {
      eventInfo: {
        type: Object
      }
    },
    components: {
      Datepicker: VueDatepickerUi
    },
    methods: {
      updateParent() {
      this.$emit('update', {
        date: this.date,
        timeStart: this.timeStart,
        timeEnd: this.timeEnd,
        })},
        
        
    }
  }
  </script>

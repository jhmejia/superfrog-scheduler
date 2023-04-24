<template class="main">
  <Datepicker
    
    v-model="eventInfo.scheduleDate"
    lang="en"
    @input="updateParent"
    date-format="{
      day: '2-digit',
      month: '2-digit',
      year: 'numeric' }"
  />
  
</template>
  
  <script>

  import 'vue-datepicker-ui/lib/vuedatepickerui.css';
  import VueDatepickerUi from 'vue-datepicker-ui';

  export default {
    name: 'DateTimePage',
    data() {
      return {
        scheduleDate: [
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
        eventInfo: this.eventInfo,
        })},
        
        
    }
  }
  </script>

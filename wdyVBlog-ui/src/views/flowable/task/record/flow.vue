<template>
  <div>
    <flow-view :xmlData="xmlData" :taskList="taskData"/>
  </div>
</template>
<script>
import FlowView from './flowview'

export default {
  name: "Flow",
  components: {
    FlowView
  },
  props: {
    xmlData: {
      type: String,
      default: ''
    },
    taskData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {};
  }
};
</script>

<script setup>
    const props=defineProps(['title'])
</script>
<template>
    <h3 class="connected">{{props.title}}</h3>
</template>
<style scoped>
    .connected {
        text-align: center;
    }
</style>
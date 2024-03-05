<script setup>
    const props=defineProps(["titleH5", "labels"]);
</script>
<template>
    <h5>
        {{ props.labels+" : "+props.titleH5 }}
    </h5>
</template>
<style scoped>
    h5 {
        font-size: 20px;
        color: #111111;
        font-weight: 700;
        margin-bottom: 8px;
    }
</style>
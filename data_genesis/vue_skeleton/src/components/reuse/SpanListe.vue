<script setup>
    const props=defineProps(["titleSpan", "labels"]);
</script>
<template>
    <span class="span_liste">
        {{ props.labels+" : "+props.titleSpan }}
    </span>
</template>
<style scoped>
    .span_liste {
        font-size: 15px;
        color: #4c57d6;
        font-weight: 500;
        display: block;
        margin-bottom: 10px;
    }
</style>
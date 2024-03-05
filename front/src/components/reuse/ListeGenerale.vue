<script setup>

</script>
<template>
    <div class="liste_computer">
        <slot></slot>
    </div>
</template>
<style scoped>

    .liste_computer {
        margin-block-start: 1%;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-template-rows: 1fr 1fr 1fr;
        grid-row-gap: 20px;
        background-color: #eeeffb;
        width: 100vw;
        height: 100vh;
    }
    
</style>
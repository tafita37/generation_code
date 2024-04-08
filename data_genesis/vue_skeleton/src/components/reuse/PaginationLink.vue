<script setup>
    const props=defineProps(["nameNumPage", "actualNumPage", "functionChangePage"]);

    const handleSubmit = () => {
        try {
            props.functionChangePage();
        } catch (error) {
            console.log(props);
            console.log(error);
        }
    };
</script>
<template>
    <li :class="{ 'page-item': true, 'active': props.nameNumPage==props.actualNumPage }">
        <a class="page-link" @click.prevent="() => handleSubmit()">
            {{ props.nameNumPage }}
        </a>
    </li>
</template>
<style scoped>  
    a {
        text-decoration: none;
        cursor: pointer;
    }
    .page-link {
        margin-left: 0;
        border-top-left-radius: 0.25rem;
        border-bottom-left-radius: 0.25rem;
    }

    .page-link {
        position: relative;
        display: block;
        padding: 0.5rem 0.75rem;
        margin-left: -1px;
        line-height: 1.25;
        color: #adb5bd;
        background-color: transparent;
        border: 1px solid transparent;
    }

    .page-link {
        position: relative;
        display: block;
        padding: 0.5rem 0.75rem;
        margin-left: -1px;
        line-height: 1.25;
        color: #adb5bd;
        background-color: transparent;
        border: 1px solid transparent;
    }

    .page-item.active .page-link {
        z-index: 3;
        color: #f8f9fa;
        background-color: #1b68ff;
        border-color: #1b68ff;
    } 
</style>
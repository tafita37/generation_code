<script setup>
    import router from '@/router';
    import PaginationLink from './PaginationLink.vue';

    const props=defineProps(["actualNumPage", "listeNumPages", "url"]);

    const changeListe = (numPage) => {
        const url=props.url;
        router.push({ 
            name: url,
            params: { 
                numPage: numPage
            } 
        });
    };
</script>
<template>
    <nav aria-label="Table Paging" class="mb-0 text-muted">
        <ul class="pagination justify-content-center mb-0">
            <PaginationLink :name-num-page="i" :actual-num-page="props.actualNumPage" v-for="i in props.listeNumPages" :key="i" :function-change-page="() => changeListe(i)"/>
        </ul>
    </nav>
</template>
<style scoped>
    .text-muted {
        color: #adb5bd !important;
        font-weight: 300;                       
    }

    .pagination {
        display: flex;
        padding-left: 0;
        list-style: none;
        border-radius: 0.25rem;
    }

    .justify-content-center {
        justify-content: center !important;
    }
</style>
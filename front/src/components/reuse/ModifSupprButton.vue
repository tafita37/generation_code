<script setup>
    import ButtonListe from './ButtonListe.vue';
    import DButtonList from './DButtonList.vue';

    const props=defineProps(['functModif', 'functDelete']);

</script>
<template>
    <DButtonList>
        <ButtonListe contain-button="Modifier" :event-click="props.functModif" bg="green"/>
        <ButtonListe contain-button="Supprimer" :event-click="props.functDelete" bg="red"/>
    </DButtonList>
</template>
<style>
    
</style>
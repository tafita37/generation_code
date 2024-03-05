<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allStockPointVente=reactive({});

    const getAllStockPointVente = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/stockPointVente/allStockPointVente';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allStockPointVente, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idStockPointVente) => {
        router.push({path : "/modifStockPointVente", query: {idStockPointVente : idStockPointVente}});
    }

    const deleteStockPointVenteById = (idStockPointVente) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/stockPointVente/deleteStockPointVenteById?idStockPointVente='+idStockPointVente;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllStockPointVente);
</script>
<template>
    <TitleListe title-liste="Liste des stockPointVente"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(stockPointVente)  in allStockPointVente" :key="stockPointVente.idStockPointVente">
            <H5TitleListe labels="" :title-h5="stockPointVente.idStockPointVente"/>
            <SpanListe :title-span='stockPointVente.idStockPointVente' labels=''/><SpanListe :title-span='stockPointVente.idPointVente.label' labels=''/><SpanListe :title-span='stockPointVente.idLaptop.label' labels=''/><SpanListe :title-span='stockPointVente.quantiteLaptopEntrant' labels=''/><SpanListe :title-span='stockPointVente.quantiteLaptopSortant' labels=''/><SpanListe :title-span='stockPointVente.dateMouvement' labels=''/><SpanListe :title-span='stockPointVente.prixAchat' labels=''/><SpanListe :title-span='stockPointVente.prixVente' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(stockPointVente.idStockPointVente)" :funct-delete="() => deleteStockPointVenteById(stockPointVente.idStockPointVente)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>

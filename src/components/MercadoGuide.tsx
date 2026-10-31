import React, { useState } from 'react';
import { Flame, Sparkles, BookOpen, Search, ShieldCheck } from 'lucide-react';
import { CHILIS_DATA, PANTRY_DATA } from '../data/pantry';
import { ChiliInfo, PantryItem } from '../types';
import { AjiHeatGauge } from './AjiHeatGauge';
import { soundFx } from '../utils/audio';

export const MercadoGuide: React.FC = () => {
  const [selectedChili, setSelectedChili] = useState<ChiliInfo>(CHILIS_DATA[0]);
  const [activeTab, setActiveTab] = useState<'chilis' | 'pantry'>('chilis');
  const [searchQuery, setSearchQuery] = useState('');

  const filteredChilis = CHILIS_DATA.filter((c) =>
    c.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    c.culinaryUse.toLowerCase().includes(searchQuery.toLowerCase()) ||
    c.flavorProfile.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const filteredPantry = PANTRY_DATA.filter((p) =>
    p.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    p.description.toLowerCase().includes(searchQuery.toLowerCase()) ||
    p.vitalFor.some((dish) => dish.toLowerCase().includes(searchQuery.toLowerCase()))
  );

  return (
    <div className="space-y-8">
      {/* Editorial Header */}
      <div className="bg-[#F5F0E8] rounded-3xl p-6 sm:p-10 border border-[#EDE6DC] relative overflow-hidden">
        <div className="max-w-2xl">
          <span className="text-xs font-bold uppercase tracking-widest text-[#8D4B00] block mb-1">
            Mercado Andino & Botica Gastronómica
          </span>
          <h1 className="font-display text-2xl sm:text-4xl font-extrabold text-[#1D1C17] leading-tight mb-3">
            El Universo de los Ajíes del Perú
          </h1>
          <p className="text-sm sm:text-base text-[#554336] leading-relaxed">
            Perú es el epicentro genético mundial del género <em>Capsicum</em>. Desde la costa árida hasta las cumbres andinas y la cuenca amazónica, cada ají define la personalidad, el aroma y la memoria de su cocina.
          </p>
          <div className="flex flex-wrap items-center gap-2 mt-4">
            <span className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-[11px] font-bold bg-[#15803D]/10 text-[#15803D] border border-[#15803D]/25">
              <ShieldCheck className="w-3.5 h-3.5" />
              <span>Guía de Mercado 100% Offline</span>
            </span>
            <span className="text-xs text-[#887364]">
              Acceso a ajíes y despensa garantizado sin internet
            </span>
          </div>
        </div>

        {/* Tab switchers (Pill button filters) */}
        <div className="flex items-center gap-2 mt-6">
          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              setActiveTab('chilis');
            }}
            className={`py-2 px-5 rounded-full text-xs font-bold transition-all ${
              activeTab === 'chilis'
                ? 'bg-[#C2410C] text-white shadow-md'
                : 'bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2]'
            }`}
          >
            Ajíes Nativos ({CHILIS_DATA.length})
          </button>
          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              setActiveTab('pantry');
            }}
            className={`py-2 px-5 rounded-full text-xs font-bold transition-all ${
              activeTab === 'pantry'
                ? 'bg-[#581C87] text-white shadow-md'
                : 'bg-[#EDE6DC] text-[#554336] hover:bg-[#E7E0D2]'
            }`}
          >
            Despensa Ancestral ({PANTRY_DATA.length})
          </button>
        </div>
      </div>

      {/* Search Bar (Pill shape M3) */}
      <div className="relative max-w-md">
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder={activeTab === 'chilis' ? 'Buscar ají por nombre, uso o picor...' : 'Buscar ingrediente de despensa...'}
          className="w-full pl-11 pr-4 py-3 rounded-full bg-[#F5F0E8] border border-[#EDE6DC] text-sm text-[#1D1C17] placeholder-[#887364] focus:outline-none focus:ring-2 focus:ring-[#8D4B00] transition-shadow shadow-inner"
        />
        <Search className="w-4 h-4 text-[#887364] absolute left-4 top-1/2 -translate-y-1/2" />
      </div>

      {activeTab === 'chilis' ? (
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
          {/* Chili cards selection list */}
          <div className="lg:col-span-5 space-y-3">
            {filteredChilis.map((chili) => {
              const isSelected = selectedChili.id === chili.id;
              return (
                <div
                  key={chili.id}
                  onClick={() => {
                    soundFx.playClick();
                    setSelectedChili(chili);
                  }}
                  className={`cursor-pointer p-4 rounded-2xl border transition-all ${
                    isSelected
                      ? 'bg-[#F5F0E8] border-[#8D4B00] shadow-md ring-2 ring-[#8D4B00]/20'
                      : 'bg-[#FEF9F1] border-[#EDE6DC] hover:bg-[#F5F0E8]'
                  }`}
                >
                  <div className="flex items-center justify-between gap-2 mb-1">
                    <h3 className="font-display text-base font-bold text-[#1D1C17]">
                      {chili.name.split(' (')[0]}
                    </h3>
                    <span className="text-[11px] font-bold text-[#8D4B00] tabular-nums">
                      {chili.scoville}
                    </span>
                  </div>

                  <div className="flex items-center justify-between">
                    <span className="text-xs text-[#554336] italic">{chili.origin}</span>
                    <AjiHeatGauge level={chili.heatLevel} size="sm" showLabel={false} />
                  </div>
                </div>
              );
            })}
          </div>

          {/* Selected Chili Deep Dive Detail Card */}
          <div className="lg:col-span-7 bg-[#F5F0E8] rounded-3xl p-6 sm:p-8 border border-[#EDE6DC] shadow-md space-y-6">
            <div className="flex flex-col sm:flex-row items-start sm:items-center justify-between gap-4 border-b border-[#EDE6DC] pb-5">
              <div>
                <span className="text-xs font-bold uppercase tracking-widest text-[#8D4B00] block">
                  Ficha Técnica Botánica
                </span>
                <h2 className="font-display text-2xl font-extrabold text-[#1D1C17] mt-0.5">
                  {selectedChili.name}
                </h2>
                <p className="text-xs text-[#887364] italic">
                  {selectedChili.scientificName} · {selectedChili.origin}
                </p>
              </div>

              <div className="p-3 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC] text-right">
                <span className="text-[10px] uppercase font-bold text-[#887364] block">Escala Scoville</span>
                <span className="text-sm font-extrabold text-[#C2410C] tabular-nums">
                  {selectedChili.scoville}
                </span>
                <div className="mt-1">
                  <AjiHeatGauge level={selectedChili.heatLevel} size="sm" showLabel={true} />
                </div>
              </div>
            </div>

            {/* Chili Photograph */}
            <div className="w-full aspect-[16/9] rounded-2xl overflow-hidden bg-[#E7E2DA] border border-[#EDE6DC]">
              <img
                src={selectedChili.imageUrl}
                alt={selectedChili.name}
                referrerPolicy="no-referrer"
                className="w-full h-full object-cover"
              />
            </div>

            {/* Flavor notes */}
            <div>
              <h3 className="text-xs font-bold uppercase tracking-wider text-[#554336] mb-1.5 flex items-center gap-1.5">
                <Sparkles className="w-3.5 h-3.5 text-[#D97706]" />
                <span>Perfil Sensorial & Bouquet</span>
              </h3>
              <p className="text-sm text-[#1D1C17] leading-relaxed bg-[#FEF9F1] p-4 rounded-2xl border border-[#EDE6DC]">
                {selectedChili.flavorProfile}
              </p>
            </div>

            {/* Culinary Use */}
            <div>
              <h3 className="text-xs font-bold uppercase tracking-wider text-[#554336] mb-1.5 flex items-center gap-1.5">
                <BookOpen className="w-3.5 h-3.5 text-[#8D4B00]" />
                <span>Uso Culinario Canónico</span>
              </h3>
              <p className="text-sm text-[#1D1C17] leading-relaxed bg-[#FEF9F1] p-4 rounded-2xl border border-[#EDE6DC]">
                {selectedChili.culinaryUse}
              </p>
            </div>

            {/* Substitution Guide */}
            <div>
              <h3 className="text-xs font-bold uppercase tracking-wider text-[#554336] mb-1.5 flex items-center gap-1.5">
                <ShieldCheck className="w-3.5 h-3.5 text-[#15803D]" />
                <span>Sustitución en el Extranjero</span>
              </h3>
              <p className="text-sm text-[#554336] leading-relaxed bg-[#F8F3EB] p-4 rounded-2xl border border-[#EDE6DC]">
                {selectedChili.substitution}
              </p>
            </div>
          </div>
        </div>
      ) : (
        /* Pantry Items Grid */
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredPantry.map((item) => (
            <div
              key={item.id}
              className="bg-[#F5F0E8] rounded-3xl p-6 border border-[#EDE6DC] shadow-sm flex flex-col justify-between"
            >
              <div>
                <span className="text-[11px] font-bold text-[#8D4B00] uppercase tracking-wider">
                  {item.category}
                </span>
                <h3 className="font-display text-xl font-bold text-[#1D1C17] mt-1 mb-0.5">
                  {item.name}
                </h3>
                <p className="text-xs font-semibold text-[#581C87] mb-3">
                  {item.localName}
                </p>
                <p className="text-xs sm:text-sm text-[#554336] leading-relaxed mb-4">
                  {item.description}
                </p>

                <div className="mb-4">
                  <span className="text-[11px] font-bold text-[#1D1C17] block mb-1">
                    Esencial para:
                  </span>
                  <div className="flex flex-wrap gap-1.5">
                    {item.vitalFor.map((dish, i) => (
                      <span
                        key={i}
                        className="text-xs px-2.5 py-1 rounded-full bg-[#EDE6DC] text-[#554336] font-medium"
                      >
                        {dish}
                      </span>
                    ))}
                  </div>
                </div>
              </div>

              <div className="p-3 bg-[#FEF9F1] rounded-2xl border border-[#EDE6DC] text-xs text-[#554336]">
                <strong className="text-[#8D4B00]">Consejo del Maestro: </strong>
                {item.tip}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

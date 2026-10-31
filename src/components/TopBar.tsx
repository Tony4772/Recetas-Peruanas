import React from 'react';
import { ChefHat, Bookmark, Flame, WifiOff } from 'lucide-react';
import { soundFx } from '../utils/audio';
import { PWAInstallButton } from './PWAInstallButton';

interface TopBarProps {
  currentTab: 'feed' | 'market' | 'saved' | 'kotlin';
  onSelectTab: (tab: 'feed' | 'market' | 'saved' | 'kotlin') => void;
  savedCount: number;
  onQuickCook: () => void;
  isDeviceFrame: boolean;
  onToggleDeviceFrame: () => void;
  isOnline?: boolean;
}

export const TopBar: React.FC<TopBarProps> = ({
  currentTab,
  onSelectTab,
  savedCount,
  onQuickCook,
  isDeviceFrame,
  onToggleDeviceFrame,
  isOnline = true,
}) => {
  return (
    <header className="sticky top-0 z-40 bg-[#FEF9F1]/90 backdrop-blur-md border-b border-[#EDE6DC] transition-colors">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between gap-6">
        {/* Zone 1: Brand wordmark (single line) */}
        <button
          type="button"
          onClick={() => {
            soundFx.playClick();
            onSelectTab('feed');
          }}
          className="flex items-center gap-2.5 text-left group focus-visible:outline-none"
        >
          <div className="w-9 h-9 rounded-2xl bg-[#D97706] text-white flex items-center justify-center shadow-md shadow-[#D97706]/20 group-hover:scale-105 transition-transform">
            <Flame className="w-5 h-5 fill-current" />
          </div>
          <span className="font-display text-lg sm:text-xl font-bold tracking-tight text-[#1D1C17] whitespace-nowrap shrink-0">
            Andean Culinary
          </span>
        </button>

        {/* Zone 2: Concise single-line navigation links */}
        <nav className="hidden md:flex items-center gap-6 lg:gap-8 text-sm font-medium text-[#554336]">
          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onSelectTab('feed');
            }}
            className={`transition-colors whitespace-nowrap shrink-0 pb-1 border-b-2 ${
              currentTab === 'feed'
                ? 'text-[#8D4B00] border-[#8D4B00] font-semibold'
                : 'border-transparent hover:text-[#1D1C17]'
            }`}
          >
            Recetario
          </button>

          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onSelectTab('market');
            }}
            className={`transition-colors whitespace-nowrap shrink-0 pb-1 border-b-2 ${
              currentTab === 'market'
                ? 'text-[#8D4B00] border-[#8D4B00] font-semibold'
                : 'border-transparent hover:text-[#1D1C17]'
            }`}
          >
            Mercado & Ajíes
          </button>

          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onSelectTab('saved');
            }}
            className={`transition-colors whitespace-nowrap shrink-0 pb-1 border-b-2 flex items-center gap-1.5 ${
              currentTab === 'saved'
                ? 'text-[#8D4B00] border-[#8D4B00] font-semibold'
                : 'border-transparent hover:text-[#1D1C17]'
            }`}
          >
            <span>Favoritos</span>
            {savedCount > 0 && (
              <span className="text-[11px] font-bold px-1.5 py-0.2 bg-[#EDE6DC] text-[#8D4B00] rounded-full tabular-nums">
                {savedCount}
              </span>
            )}
          </button>

          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onSelectTab('kotlin');
            }}
            className={`transition-colors whitespace-nowrap shrink-0 pb-1 border-b-2 flex items-center gap-1.5 ${
              currentTab === 'kotlin'
                ? 'text-[#15803D] border-[#15803D] font-bold'
                : 'border-transparent text-[#15803D] hover:text-[#0f5a2b]'
            }`}
          >
            <span className="w-2 h-2 rounded-full bg-[#15803D]" />
            <span>Código Kotlin + Compose</span>
          </button>
        </nav>

        {/* Zone 3: Actions zone */}
        <div className="flex items-center gap-2 sm:gap-3 shrink-0">
          {!isOnline && (
            <span className="hidden sm:inline-flex items-center gap-1 px-2.5 py-1 bg-[#9A3412] text-white text-[11px] font-bold rounded-full">
              <WifiOff className="w-3 h-3" />
              <span>Offline</span>
            </span>
          )}

          <PWAInstallButton variant="header" />

          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onToggleDeviceFrame();
            }}
            className={`px-3 py-1.5 text-xs font-semibold rounded-full border transition-colors whitespace-nowrap shrink-0 hidden sm:flex items-center gap-1.5 ${
              isDeviceFrame
                ? 'bg-[#1D1C17] text-white border-[#1D1C17]'
                : 'bg-[#EDE6DC] text-[#554336] border-[#dbc2b0] hover:bg-[#E7E0D2]'
            }`}
            title="Alternar vista marco Android Pixel / Pantalla completa"
          >
            <span>📱</span>
            <span>{isDeviceFrame ? 'Modo Android' : 'Pantalla Completa'}</span>
          </button>

          <button
            type="button"
            onClick={() => {
              soundFx.playClick();
              onQuickCook();
            }}
            className="px-4 py-2 text-xs font-semibold text-white bg-[#D97706] hover:bg-[#B45309] rounded-full shadow-md shadow-[#D97706]/20 transition-all active:scale-95 flex items-center gap-1.5 whitespace-nowrap shrink-0"
          >
            <ChefHat className="w-4 h-4" />
            <span>Cocinar</span>
          </button>
        </div>
      </div>
    </header>
  );
};

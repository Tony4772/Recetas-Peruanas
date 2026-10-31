import React, { useState } from 'react';
import { Download, Smartphone, Share, PlusSquare, X, CheckCircle2 } from 'lucide-react';
import { usePWAInstall } from '../hooks/usePWAInstall';
import { soundFx } from '../utils/audio';

interface PWAInstallButtonProps {
  variant?: 'header' | 'hero' | 'drawer';
  className?: string;
}

export const PWAInstallButton: React.FC<PWAInstallButtonProps> = ({
  variant = 'header',
  className = '',
}) => {
  const { isInstallable, isInstalled, isIOS, install } = usePWAInstall();
  const [showIOSModal, setShowIOSModal] = useState(false);
  const [installSuccess, setInstallSuccess] = useState(false);

  // If already installed and not testing, do not show button
  if (isInstalled) {
    return (
      <span className="hidden sm:inline-flex items-center gap-1.5 px-3 py-1 bg-[#15803D]/10 text-[#15803D] rounded-full text-xs font-semibold">
        <CheckCircle2 className="w-3.5 h-3.5" />
        <span>App Instalada</span>
      </span>
    );
  }

  const handleInstallClick = async () => {
    soundFx.playClick();
    if (isIOS) {
      setShowIOSModal(true);
      return;
    }

    if (isInstallable) {
      const success = await install();
      if (success) {
        setInstallSuccess(true);
        setTimeout(() => setInstallSuccess(false), 3000);
      }
    } else {
      // Fallback: explain how to install or add to desktop/home
      setShowIOSModal(true);
    }
  };

  const buttonContent = (
    <>
      <Download className="w-3.5 h-3.5 text-current animate-bounce" />
      <span>Instalar App</span>
    </>
  );

  return (
    <>
      {variant === 'header' && (
        <button
          type="button"
          onClick={handleInstallClick}
          className={`px-3 py-1.5 text-xs font-bold rounded-full bg-[#8D4B00]/10 hover:bg-[#8D4B00]/20 text-[#8D4B00] border border-[#8D4B00]/30 transition-all flex items-center gap-1.5 whitespace-nowrap shadow-sm active:scale-95 ${className}`}
          title="Instalar Andean Culinary como aplicación nativa offline"
        >
          {buttonContent}
        </button>
      )}

      {variant === 'hero' && (
        <button
          type="button"
          onClick={handleInstallClick}
          className={`py-3 px-5 rounded-full bg-[#1D1C17] hover:bg-[#292524] text-white text-xs sm:text-sm font-bold flex items-center gap-2 shadow-md transition-all active:scale-95 ${className}`}
        >
          <Smartphone className="w-4 h-4 text-amber-400" />
          <span>Instalar App para Cocina Offline</span>
        </button>
      )}

      {variant === 'drawer' && (
        <button
          type="button"
          onClick={handleInstallClick}
          className={`w-full py-3 px-4 rounded-2xl bg-[#8D4B00] text-white font-bold text-xs flex items-center justify-center gap-2 shadow-md ${className}`}
        >
          <Download className="w-4 h-4" />
          <span>Instalar App en tu Celular</span>
        </button>
      )}

      {/* iOS or Desktop Installation Instruction Dialog */}
      {showIOSModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/60 backdrop-blur-sm animate-fadeIn">
          <div className="bg-[#FEF9F1] rounded-3xl max-w-sm w-full p-6 border border-[#EDE6DC] shadow-2xl relative space-y-4">
            <button
              type="button"
              onClick={() => {
                soundFx.playClick();
                setShowIOSModal(false);
              }}
              className="absolute top-4 right-4 p-1.5 rounded-full text-[#887364] hover:bg-[#EDE6DC] transition-colors"
            >
              <X className="w-5 h-5" />
            </button>

            <div className="flex items-center gap-3">
              <div className="w-12 h-12 rounded-2xl bg-[#8D4B00] text-white flex items-center justify-center shadow-md">
                <Smartphone className="w-6 h-6" />
              </div>
              <div>
                <h3 className="font-display font-bold text-base text-[#1D1C17]">
                  Instalar Andean Culinary
                </h3>
                <p className="text-xs text-[#887364]">
                  Accede a las 52 recetas sin conexión
                </p>
              </div>
            </div>

            <div className="space-y-3 pt-2 text-xs text-[#554336]">
              <div className="flex items-start gap-3 p-3 rounded-2xl bg-[#F5F0E8] border border-[#EDE6DC]">
                <div className="w-6 h-6 rounded-full bg-[#8D4B00] text-white flex items-center justify-center font-bold text-xs shrink-0 mt-0.5">
                  1
                </div>
                <div>
                  <p className="font-bold text-[#1D1C17] flex items-center gap-1.5">
                    Toca en Compartir <Share className="w-3.5 h-3.5 text-[#8D4B00]" />
                  </p>
                  <p className="text-[11px] text-[#887364]">
                    En la barra inferior de Safari o en el menú de opciones de tu navegador.
                  </p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 rounded-2xl bg-[#F5F0E8] border border-[#EDE6DC]">
                <div className="w-6 h-6 rounded-full bg-[#8D4B00] text-white flex items-center justify-center font-bold text-xs shrink-0 mt-0.5">
                  2
                </div>
                <div>
                  <p className="font-bold text-[#1D1C17] flex items-center gap-1.5">
                    Agregar a Inicio <PlusSquare className="w-3.5 h-3.5 text-[#8D4B00]" />
                  </p>
                  <p className="text-[11px] text-[#887364]">
                    Desliza hacia abajo y selecciona &quot;Agregar a la pantalla de inicio&quot;.
                  </p>
                </div>
              </div>

              <div className="flex items-start gap-3 p-3 rounded-2xl bg-[#F5F0E8] border border-[#EDE6DC]">
                <div className="w-6 h-6 rounded-full bg-[#8D4B00] text-white flex items-center justify-center font-bold text-xs shrink-0 mt-0.5">
                  3
                </div>
                <div>
                  <p className="font-bold text-[#1D1C17]">
                    ¡Listo para cocinar offline!
                  </p>
                  <p className="text-[11px] text-[#887364]">
                    Ábrelo desde tu pantalla de inicio como una aplicación nativa.
                  </p>
                </div>
              </div>
            </div>

            <button
              type="button"
              onClick={() => {
                soundFx.playClick();
                setShowIOSModal(false);
              }}
              className="w-full py-2.5 rounded-full bg-[#8D4B00] hover:bg-[#723c00] text-white font-bold text-xs shadow-md transition-all active:scale-95"
            >
              Entendido
            </button>
          </div>
        </div>
      )}
    </>
  );
};

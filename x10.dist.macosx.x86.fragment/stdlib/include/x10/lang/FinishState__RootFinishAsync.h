#ifndef __X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H
#define __X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#undef X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#define X10_LANG_SIMPLELATCH_H_NODEPS
#include <x10/lang/SimpleLatch.h>
#undef X10_LANG_SIMPLELATCH_H_NODEPS
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class MultipleExceptions;
} } 
namespace x10 { namespace lang { 

class FinishState__RootFinishAsync : public x10::lang::FinishState__RootFinishSkeleton
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Runtime__Mortal::itable<x10::lang::FinishState__RootFinishAsync > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__RootFinishAsync > _itable_1;
    
    virtual x10_boolean _isMortal() { return true; }
    
    void _instance_init();
    
    x10::lang::SimpleLatch _Embed_latch;
    x10aux::ref<x10::lang::SimpleLatch> FMGL(latch);
    
    x10aux::ref<x10::lang::Throwable> FMGL(exception);
    
    virtual void notifySubActivitySpawn(x10::lang::Place place);
    virtual void notifyActivityTermination();
    virtual void pushException(x10aux::ref<x10::lang::Throwable> t);
    virtual void waitForFinish();
    virtual x10aux::ref<x10::lang::SimpleLatch> simpleLatch();
    virtual x10aux::ref<x10::lang::FinishState__RootFinishAsync> x10__lang__FinishState__RootFinishAsync____x10__lang__FinishState__RootFinishAsync__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::lang::FinishState__RootFinishAsync> _make(
             );
    
    void __fieldInitializers1924();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H

namespace x10 { namespace lang { 
class FinishState__RootFinishAsync;
} } 

#ifndef X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_NODEPS
#define X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_NODEPS
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#include <x10/lang/SimpleLatch.h>
#include <x10/compiler/Embed.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Place.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/MultipleExceptions.h>
#ifndef X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_GENERICS
#define X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::FinishState__RootFinishAsync::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__RootFinishAsync> this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RootFinishAsync>(), 0, sizeof(x10::lang::FinishState__RootFinishAsync))) x10::lang::FinishState__RootFinishAsync();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__ROOTFINISHASYNC_H_NODEPS

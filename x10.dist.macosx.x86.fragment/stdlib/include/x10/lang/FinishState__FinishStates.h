#ifndef __X10_LANG_FINISHSTATE__FINISHSTATES_H
#define __X10_LANG_FINISHSTATE__FINISHSTATES_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_LOCK_H_NODEPS
#include <x10/lang/Lock.h>
#undef X10_LANG_LOCK_H_NODEPS
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 

class FinishState__FinishStates : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::util::HashMap<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> >, x10aux::ref<x10::lang::FinishState> > >
      FMGL(map);
    
    x10::lang::Lock _Embed_lock;
    x10aux::ref<x10::lang::Lock> FMGL(lock);
    
    virtual x10aux::ref<x10::lang::FinishState> __apply(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > root,
                                                        x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState> > > factory);
    virtual void remove(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > root);
    virtual x10aux::ref<x10::lang::FinishState__FinishStates>
      x10__lang__FinishState__FinishStates____x10__lang__FinishState__FinishStates__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::lang::FinishState__FinishStates> _make(
             );
    
    void __fieldInitializers1926();
    
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
#endif // X10_LANG_FINISHSTATE__FINISHSTATES_H

namespace x10 { namespace lang { 
class FinishState__FinishStates;
} } 

#ifndef X10_LANG_FINISHSTATE__FINISHSTATES_H_NODEPS
#define X10_LANG_FINISHSTATE__FINISHSTATES_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Lock.h>
#include <x10/util/HashMap.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/compiler/Embed.h>
#include <x10/lang/Fun_0_0.h>
#ifndef X10_LANG_FINISHSTATE__FINISHSTATES_H_GENERICS
#define X10_LANG_FINISHSTATE__FINISHSTATES_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::FinishState__FinishStates::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__FinishStates> this_ = new (memset(x10aux::alloc<x10::lang::FinishState__FinishStates>(), 0, sizeof(x10::lang::FinishState__FinishStates))) x10::lang::FinishState__FinishStates();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__FINISHSTATES_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__FINISHSTATES_H_NODEPS
